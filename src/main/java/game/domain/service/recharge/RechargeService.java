package game.domain.service.recharge;

import game.application.moneydetailed.command.CreateMoneyDetailedCommand;
import game.application.recharge.command.CreateRechargeCommand;
import game.application.recharge.command.ListRechargeCommand;
import game.core.common.id.IdFactory;
import game.core.enums.FlowType;
import game.core.enums.YesOrNoStatus;
import game.core.util.CoreDateUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.recharge.IRechargeRepository;
import game.domain.model.recharge.Recharge;
import game.domain.model.recharge.RechargeGive;
import game.domain.model.system.System;
import game.domain.model.user.User;
import game.domain.service.moneydetailed.IMoneyDetailedService;
import game.domain.service.system.ISystemService;
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
import java.util.*;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("rechargeService")
public class RechargeService implements IRechargeService {

    private final IRechargeRepository<Recharge, String> rechargeRepository;

    private final IUserService userService;

    private final IRechargeGiveService rechargeGiveService;
    private final IdFactory idFactory;
    private final ISystemService systemService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    public RechargeService(IRechargeRepository<Recharge, String> rechargeRepository, IUserService userService, IRechargeGiveService rechargeGiveService, IdFactory idFactory, ISystemService systemService) {
        this.rechargeRepository = rechargeRepository;
        this.userService = userService;
        this.rechargeGiveService = rechargeGiveService;
        this.idFactory = idFactory;
        this.systemService = systemService;
    }

    @Override
    public Pagination<Recharge> pagination(ListRechargeCommand command) {
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
        if (null != command.getIsSuccess() && command.getIsSuccess() != YesOrNoStatus.ALL) {
            criterionList.add(Restrictions.eq("isSuccess", command.getIsSuccess()));
        } else {
            criterionList.add(Restrictions.or(Restrictions.eq("isSuccess", YesOrNoStatus.YES), Restrictions.and(Restrictions.eq("isSuccess", YesOrNoStatus.NO), Restrictions.ge("createDate", CoreDateUtils.addDay(new Date(), -3)))));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return rechargeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public List<Recharge> list(ListRechargeCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
            aliasMap.put("user", "user");
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return rechargeRepository.list(criterionList, orderList, null, null, aliasMap, 100);
    }

    @Override
    public Recharge pay(CreateRechargeCommand command) {

        User user = userService.searchByID(command.getUserId());
        String rechargeNo;
        while (true) {
            rechargeNo = idFactory.getNextId();
            Recharge recharge = rechargeRepository.byRechargeNo(rechargeNo);
            if (null == recharge) {
                break;
            }
        }
        Recharge recharge = new Recharge(rechargeNo, user, command.getMoney(), YesOrNoStatus.NO, command.getPayType());

        rechargeRepository.save(recharge);
        return recharge;
    }

    @Override
    public Recharge getById(String agent_bill_id) {
        return rechargeRepository.getById(agent_bill_id);
    }

    @Override
    public BigDecimal totalMoney(ListRechargeCommand command) {
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
        if (null != command.getIsSuccess() && command.getIsSuccess() != YesOrNoStatus.ALL) {
            criterionList.add(Restrictions.eq("isSuccess", command.getIsSuccess()));
        }
        return rechargeRepository.total(criterionList, aliasMap);
    }

    @Override
    public BigDecimal totalMoneyEq(ListRechargeCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
            aliasMap.put("user", "user");
        }
        if (!CoreStringUtils.isEmpty(command.getStartDate()) && null != CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")));
        }
        if (null != command.getIsSuccess() && command.getIsSuccess() != YesOrNoStatus.ALL) {
            criterionList.add(Restrictions.eq("isSuccess", command.getIsSuccess()));
        }
        return rechargeRepository.total(criterionList, aliasMap);
    }

    @Override
    public boolean payNotify(String payid, String orderNo, String amount) {
        Recharge recharge = rechargeRepository.byRechargeNo(payid);
        if (null != recharge && recharge.getIsSuccess() == YesOrNoStatus.NO &&
                recharge.getMoney().setScale(2, RoundingMode.HALF_UP).floatValue() == new Float(amount)) {
            recharge.changeIsSuccess(YesOrNoStatus.YES);
            recharge.changePayNo(orderNo);
            recharge.changePayTime(new Date());
            rechargeRepository.save(recharge);

            System system = systemService.getSystem();
            if (null != system && null != system.getRatio()) {

                BigDecimal addMoney = recharge.getMoney().multiply(system.getRatio());

                List<RechargeGive> rechargeGives = rechargeGiveService.list();
                for (RechargeGive rechargeGive : rechargeGives) {
                    if (rechargeGive.getMoney().compareTo(addMoney) < 0) {
                        addMoney = addMoney.add(rechargeGive.getGiveMoney());
                        break;
                    }
                }
                CreateMoneyDetailedCommand command = new CreateMoneyDetailedCommand();
                command.setFlowType(FlowType.IN_FLOW);
                command.setMoney(addMoney);
                command.setUserName(recharge.getUser().getUserName());
                command.setDescription("充值" + recharge.getMoney() + "," + recharge.getId());
                moneyDetailedService.create(command);
            }
            return true;
        }
        return false;
    }

    @Override
    public Pagination<Recharge> paginationEq(ListRechargeCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
            aliasMap.put("user", "user");
        }
        if (!CoreStringUtils.isEmpty(command.getStartDate()) && null != CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")));
        }
        if (null != command.getIsSuccess() && command.getIsSuccess() != YesOrNoStatus.ALL) {
            criterionList.add(Restrictions.eq("isSuccess", command.getIsSuccess()));
        } else {
            criterionList.add(Restrictions.or(Restrictions.eq("isSuccess", YesOrNoStatus.YES), Restrictions.and(Restrictions.eq("isSuccess", YesOrNoStatus.NO), Restrictions.ge("createDate", CoreDateUtils.addDay(new Date(), -3)))));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return rechargeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }
}
