package game.domain.service.moneydetailed;

import game.application.moneydetailed.command.CreateCommand;
import game.application.moneydetailed.command.ListCommand;
import game.core.enums.FlowType;
import game.core.util.CoreDateUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.moneydetailed.IMoneyDetailedRepository;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.domain.model.user.User;
import game.domain.service.rewarddetailed.IRewardDetailedService;
import game.domain.service.user.IUserService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("moneyDetailedService")
public class MoneyDetailedService implements IMoneyDetailedService {

    private final IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository;
    private final IRewardDetailedService rewardDetailedService;

    @Autowired
    private IUserService userService;

    @Autowired
    public MoneyDetailedService(IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository, IRewardDetailedService rewardDetailedService) {
        this.moneyDetailedRepository = moneyDetailedRepository;
        this.rewardDetailedService = rewardDetailedService;
    }

    @Override
    public void create(CreateCommand command) {
        User user = userService.searchByUserId(command.getUserId());

        if (command.getFlowType() == FlowType.IN_FLOW) {
            int oldMoney = user.getMoney();

            user.setMoney(user.getMoney() + command.getMoney());

            if (command.getDescription().contains("开房间退回")) {
                if (user.getTodayCreateGameCount() > 0) {
                    user.setTodayCreateGameCount(user.getTodayCreateGameCount() - 1);
                }
            }
            userService.update(user);
            MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            moneyDetailedRepository.save(moneyDetailed);

            if (null != user.getParent() && (command.getDescription().contains("AA支付") || command.getDescription().contains("开房间"))) {
                game.application.rewarddetailed.command.CreateCommand createCommand = new game.application.rewarddetailed.command.CreateCommand();
                createCommand.setFlowType(FlowType.OUT_FLOW);
                createCommand.setDescription(user.getUserId() + command.getDescription());
                createCommand.setMoney(BigDecimal.valueOf(command.getMoney()).divide(BigDecimal.valueOf(10), 2, RoundingMode.HALF_UP));
                createCommand.setUserId(user.getParent().getUserId());
                rewardDetailedService.create(createCommand);
            }
        } else {
            int oldMoney = user.getMoney();

            user.setMoney(user.getMoney() - command.getMoney());

            userService.update(user);

            MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            moneyDetailedRepository.save(moneyDetailed);

            if (null != user.getParent() && (command.getDescription().contains("AA支付") || command.getDescription().contains("开房间"))) {
                game.application.rewarddetailed.command.CreateCommand createCommand = new game.application.rewarddetailed.command.CreateCommand();
                createCommand.setFlowType(FlowType.IN_FLOW);
                createCommand.setDescription(user.getUserId() + command.getDescription());
                createCommand.setMoney(BigDecimal.valueOf(command.getMoney()).divide(BigDecimal.valueOf(10), 2, RoundingMode.HALF_UP));
                createCommand.setUserId(user.getParent().getUserId());
                rewardDetailedService.create(createCommand);
            }
        }
    }

    @Override
    public Pagination<MoneyDetailed> pagination(ListCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("user.userName", command.getUserName(), MatchMode.ANYWHERE));
            aliasMap.put("user", "user");
        }
        if (!CoreStringUtils.isEmpty(command.getStartDate()) && null != CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")));
        }
        if (null != command.getFlowType() && command.getFlowType() != FlowType.ALL) {
            criterionList.add(Restrictions.eq("flowType", command.getFlowType()));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return moneyDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

}
