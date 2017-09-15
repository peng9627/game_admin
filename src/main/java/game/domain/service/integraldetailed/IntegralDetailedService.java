package game.domain.service.integraldetailed;

import game.application.integraldetailed.command.CreateCommand;
import game.application.integraldetailed.command.ListCommand;
import game.core.enums.FlowType;
import game.core.util.CoreDateUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.integraldetailed.IIntegralDetailedRepository;
import game.domain.model.integraldetailed.IntegralDetailed;
import game.domain.model.user.User;
import game.domain.service.user.IUserService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("integralDetailedService")
public class IntegralDetailedService implements IIntegralDetailedService {

    private final IIntegralDetailedRepository<IntegralDetailed, String> integralDetailedRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    public IntegralDetailedService(IIntegralDetailedRepository<IntegralDetailed, String> integralDetailedRepository) {
        this.integralDetailedRepository = integralDetailedRepository;
    }

    @Override
    public void create(CreateCommand command) {
        User user = userService.searchByUserId(command.getUserId());

        if (command.getFlowType() == FlowType.IN_FLOW) {
            int oldMoney = user.getMoney();

            user.setIntegral(user.getIntegral() + command.getMoney());

            userService.update(user);

            IntegralDetailed integralDetailed = new IntegralDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            integralDetailedRepository.save(integralDetailed);
        } else {
            int oldMoney = user.getMoney();

            user.setIntegral(user.getIntegral() - command.getMoney());

            userService.update(user);

            IntegralDetailed integralDetailed = new IntegralDetailed(user, command.getFlowType(), command.getMoney(), command.getDescription(), oldMoney, user.getMoney());
            integralDetailedRepository.save(integralDetailed);
        }
    }

    @Override
    public Pagination<IntegralDetailed> pagination(ListCommand command) {
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
        return integralDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

}
