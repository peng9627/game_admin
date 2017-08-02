package game.domain.service.moneydetailed;

import game.application.moneydetailed.command.CreateMoneyDetailedCommand;
import game.application.moneydetailed.command.ListMoneyDetailedCommand;
import game.core.enums.FlowType;
import game.core.util.CoreDateUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.moneydetailed.IMoneyDetailedRepository;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.domain.model.user.User;
import game.domain.service.user.IUserService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("moneyDetailedService")
public class MoneyDetailedService implements IMoneyDetailedService {

    private final IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository;

    private final IUserService userService;

    @Autowired
    public MoneyDetailedService(IUserService userService, IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository) {
        this.userService = userService;
        this.moneyDetailedRepository = moneyDetailedRepository;
    }

    @Override
    public void create(CreateMoneyDetailedCommand command) {
        User user = userService.searchByName(command.getUserName(), LockMode.READ);

        if (command.getFlowType() == FlowType.IN_FLOW) {
            BigDecimal oldMoney = user.getMoney();

            user.changeMoney(user.getMoney().add(command.getMoney()));

            userService.update(user);

            MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            moneyDetailedRepository.save(moneyDetailed);
        } else {
            BigDecimal oldMoney = user.getMoney();

            user.changeMoney(user.getMoney().subtract(command.getMoney()));

            userService.update(user);

            MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            moneyDetailedRepository.save(moneyDetailed);
        }
    }

    @Override
    public Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command) {
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

    @Override
    public boolean receiveTask(ListMoneyDetailedCommand command) {

        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
        aliasMap.put("user", "user");
        criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDateStart(CoreDateUtils.formatDate(new Date(), "yyyy-MM-dd"))));
        criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDateEnd(CoreDateUtils.formatDate(new Date(), "yyyy-MM-dd"))));
        criterionList.add(Restrictions.eq("description", command.getDescription()));
        return moneyDetailedRepository.list(criterionList, null, null, null, aliasMap).size() > 0;
    }
}
