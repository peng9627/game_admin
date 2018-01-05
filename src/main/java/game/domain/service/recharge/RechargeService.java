package game.domain.service.recharge;

import com.alibaba.fastjson.JSONObject;
import game.application.moneydetailed.command.CreateCommand;
import game.application.recharge.command.CreateRechargeCommand;
import game.application.recharge.command.ListRechargeCommand;
import game.core.common.id.IdFactory;
import game.core.enums.FlowType;
import game.core.enums.YesOrNoStatus;
import game.core.exception.NoFoundException;
import game.core.pay.GameServer;
import game.core.pay.wechat.WechatNotify;
import game.core.util.CoreDateUtils;
import game.core.util.CoreHttpUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.recharge.IRechargeRepository;
import game.domain.model.recharge.Recharge;
import game.domain.model.recharge.RechargeSelect;
import game.domain.model.system.System;
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

    private final IdFactory idFactory;
    private final ISystemService systemService;
    private final GameServer gameServer;
    private final IRechargeSelectService rechargeSelectService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    public RechargeService(IRechargeRepository<Recharge, String> rechargeRepository, IUserService userService, IdFactory idFactory, ISystemService systemService, GameServer gameServer, IRechargeSelectService rechargeSelectService) {
        this.rechargeRepository = rechargeRepository;
        this.userService = userService;
        this.idFactory = idFactory;
        this.systemService = systemService;
        this.gameServer = gameServer;
        this.rechargeSelectService = rechargeSelectService;
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

        String rechargeNo;
        while (true) {
            rechargeNo = idFactory.getNextId();
            Recharge recharge = rechargeRepository.byRechargeNo(rechargeNo);
            if (null == recharge) {
                break;
            }
        }
        Recharge recharge = new Recharge(rechargeNo, command.getUserId(), command.getMoney(), YesOrNoStatus.NO, command.getPayType());

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

            System system = systemService.info();
//            if (null != system && null != system.getRatio()) {

//                BigDecimal addMoney = recharge.getMoney().multiply(system.getRatio());
            BigDecimal addMoney = recharge.getMoney();
            CreateCommand command = new CreateCommand();
            command.setFlowType(FlowType.IN_FLOW);
            command.setMoney(addMoney.intValue());
            command.setUserId(recharge.getUserId());
            command.setDescription("充值" + recharge.getMoney() + "," + recharge.getId());
            moneyDetailedService.create(command);
//            }
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

    @Override
    public Recharge recharge(CreateRechargeCommand command) {
        RechargeSelect rechargeSelect = rechargeSelectService.getById(command.getId());
        if (null == rechargeSelect) {
            throw new NoFoundException("id为" + command.getId() + "的记录不存在");
        }
        String no = idFactory.getNextId();
        Recharge recharge = new Recharge(no + rechargeSelect.getType(), command.getUserId(), rechargeSelect.getPrice(), YesOrNoStatus.NO, command.getPayType());
        rechargeRepository.save(recharge);
        return recharge;
    }

    @Override
    public void apiWechatSuccess(WechatNotify notify) {
        Recharge recharge = this.searchByNo(notify.getOut_trade_no());
        if (null != recharge && null == recharge.getPayTime() && 0 != recharge.getIsSuccess().compareTo(YesOrNoStatus.YES)) {
            recharge.changePayTime(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            recharge.changePayNo(notify.getTransaction_id());
            recharge.changeIsSuccess(YesOrNoStatus.YES);
            recharge.changePayNo(notify.getTransaction_id());
            rechargeRepository.update(recharge);

            System system = systemService.info();
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("manager", 998);
                jsonObject.put("target", recharge.getUserId());
                if ("1".equals(recharge.getRechargeNo().substring(recharge.getRechargeNo().length() - 1))) {
                    jsonObject.put("card", recharge.getMoney().multiply(system.getRechargeRatio()));
                    jsonObject.put("enc", CoreStringUtils.md5(998 + "&_&" + 0 + "&_&" + recharge.getUserId() + "&_&" + recharge.getMoney().multiply(system.getRechargeRatio()) + "&_&" + 0 + "&_&" + gameServer.getKey(), 32, false, "utf-8"));
                } else {
                    jsonObject.put("gold", recharge.getMoney().multiply(system.getRechargeRatio()));
                    jsonObject.put("enc", CoreStringUtils.md5(998 + "&_&" + 0 + "&_&" + recharge.getUserId() + "&_&" + 0 + "&_&" + recharge.getMoney().multiply(system.getRechargeRatio()) + "&_&" + gameServer.getKey(), 32, false, "utf-8"));
                }
                String s = CoreHttpUtils.urlConnection(gameServer.getUrl(), "add_card=" + jsonObject.toJSONString());
                if (!CoreStringUtils.isEmpty(s)) {
                    JSONObject result = JSONObject.parseObject(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Recharge searchByNo(String rechargeNo) {
        return rechargeRepository.searchByNo(rechargeNo);
    }
}
