package niuniu.domain.service.golddetailed;

import niuniu.application.golddetailed.command.CreateGoldDetailedCommand;
import niuniu.application.golddetailed.command.ListGoldDetailedCommand;
import niuniu.core.enums.FlowType;
import niuniu.core.util.CoreDateUtils;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.golddetailed.GoldDetailed;
import niuniu.domain.model.golddetailed.IGoldDetailedRepository;
import niuniu.domain.model.user.User;
import niuniu.domain.service.user.IUserService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
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
@Service("goldDetailedService")
public class GoldDetailedService implements IGoldDetailedService {

    private final IGoldDetailedRepository<GoldDetailed, String> goldDetailedRepository;

    private final IUserService userService;

    @Autowired
    public GoldDetailedService(IUserService userService, IGoldDetailedRepository<GoldDetailed, String> goldDetailedRepository) {
        this.userService = userService;
        this.goldDetailedRepository = goldDetailedRepository;
    }

    @Override
    public void create(CreateGoldDetailedCommand command) {
        User user = userService.searchByName(command.getUserName(), LockMode.READ);

        if (command.getFlowType() == FlowType.IN_FLOW) {
            BigDecimal oldGold = user.getGold();

            user.setGold(user.getGold().add(command.getGold()));

            userService.update(user);

            GoldDetailed goldDetailed = new GoldDetailed(user, command.getFlowType(), command.getGold(), command.getDescription(), oldGold, user.getGold());
            goldDetailedRepository.save(goldDetailed);
        } else {
            BigDecimal oldGold = user.getGold();

            user.setGold(user.getGold().subtract(command.getGold()));

            userService.update(user);

            GoldDetailed goldDetailed = new GoldDetailed(user, command.getFlowType(), command.getGold(), command.getDescription(), oldGold, user.getGold());
            goldDetailedRepository.save(goldDetailed);
        }
    }

    @Override
    public Pagination<GoldDetailed> pagination(ListGoldDetailedCommand command) {
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
        return goldDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public boolean receiveTask(ListGoldDetailedCommand command) {

        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
        aliasMap.put("user", "user");
        criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDateStart(CoreDateUtils.formatDate(new Date(), "yyyy-MM-dd"))));
        criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDateEnd(CoreDateUtils.formatDate(new Date(), "yyyy-MM-dd"))));
        criterionList.add(Restrictions.eq("description", command.getDescription()));
        return goldDetailedRepository.list(criterionList, null, null, null, aliasMap).size() > 0;
    }
}
