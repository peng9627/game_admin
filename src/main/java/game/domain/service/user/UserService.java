package game.domain.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import game.application.moneydetailed.command.CreateCommand;
import game.application.user.command.EditCommand;
import game.application.user.command.ListCommand;
import game.application.user.command.LoginCommand;
import game.core.api.SocketRequest;
import game.core.enums.EnableStatus;
import game.core.enums.FlowType;
import game.core.exception.ExistException;
import game.core.exception.NoFoundException;
import game.core.util.CoreDateUtils;
import game.core.util.CoreHttpUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.system.System;
import game.domain.model.user.IUserRepository;
import game.domain.model.user.User;
import game.domain.service.moneydetailed.IMoneyDetailedService;
import game.domain.service.system.ISystemService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
@Service("userService")
public class UserService implements IUserService {

    private final IUserRepository<User, String> userRepository;

    private final ISystemService systemService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    public UserService(IUserRepository<User, String> userRepository, ISystemService systemService) {
        this.userRepository = userRepository;
        this.systemService = systemService;
    }


    @Override
    public Pagination<User> pagination(ListCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getNickName())) {
            criterionList.add(Restrictions.like("name", command.getNickName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getParent())) {
            criterionList.add(Restrictions.eq("parent.id", command.getParent()));
        }
        if (!CoreStringUtils.isEmpty(command.getDeviceNo())) {
            criterionList.add(Restrictions.like("deviceNo", command.getDeviceNo(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getParentName())) {
            criterionList.add(Restrictions.like("parent.userName", command.getParentName(), MatchMode.ANYWHERE));
            aliasMap.put("parent", "parent");
        }
        if (null != command.getVip()) {
            criterionList.add(Restrictions.eq("vip", command.getVip()));
        }
        List<Order> orderList = new ArrayList<>();
        if (!CoreStringUtils.isEmpty(command.getOrder())) {
            orderList.add(Order.desc(command.getOrder()));
        } else {
            orderList.add(Order.desc("createDate"));
        }
        return userRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public User searchByUserId(int userId) {
        return userRepository.searchByUserId(userId);
    }

    @Override
    public User searchByID(String id) {
        User user = userRepository.getById(id);
        if (null == user) {
            throw new NoFoundException("没有找到ID[" + id + "]的User数据");
        }
        return user;
    }

    @Override
    public User searchByWechat(String wechat) {
        return userRepository.searchByWechat(wechat);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User weChatLogin(LoginCommand command) {
        User user = searchByWechat(command.getWeChatNo());
        if (null == user) {
            int userId;
            while (true) {
                int temp = new Random().nextInt(900000) + 100000;
                if (null == searchByUserId(temp)) {
                    userId = temp;
                    break;
                }
            }
            user = new User(userId, command.getWeChatNo());
            user.setRegisterIp(command.getIp());
            userRepository.save(user);

            System system = systemService.info();
            CreateCommand createCommand = new CreateCommand();
            createCommand.setUserId(userId);
            createCommand.setFlowType(FlowType.IN_FLOW);
            createCommand.setMoney(system.getRegisterGive());
            createCommand.setDescription("分享送钻石");
            moneyDetailedService.create(createCommand);
        } else {
            if (!CoreDateUtils.isSameDay(new Date(), user.getLastLoginDate())) {
                user.setTodayGameCount(0);
            }
        }
        user.setAgent(command.getAgent());
        user.setArea(command.getArea());
        user.setHead(command.getHead());
        user.setLastLoginDate(new Date());
        user.setLastLoginIp(command.getIp());
        try {
            user.setNicknameByte(userRepository.getSession().getLobHelper().createBlob(command.getNickname().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setSex(command.getSex());

        userRepository.save(user);
        return user;
    }

    @Override
    public void addGameCount(int userId) {
        User user = searchByUserId(userId);
        user.setGameCount(user.getGameCount() + 1);
        user.setTodayGameCount(user.getTodayGameCount() + 1);
        if (user.getTodayGameCount() == 100) {
            user.setIntegral(user.getIntegral() + 100);
            SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullBooleanAsFalse};
            int ss = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
            SocketRequest socketRequest = new SocketRequest();
            socketRequest.setUserId(userId);
            CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10410/1", JSON.toJSONString(socketRequest, ss, features));
        }
        userRepository.save(user);
    }

    @Override
    public void share(Integer userId) {
        User user = searchByUserId(userId);
        if (user.getShared()) {
            throw new ExistException();
        }
        user.setShared(true);

        System system = systemService.info();
        CreateCommand createCommand = new CreateCommand();
        createCommand.setUserId(userId);
        createCommand.setFlowType(FlowType.IN_FLOW);
        createCommand.setMoney(system.getShareGive());
        createCommand.setDescription("分享送钻石");
        moneyDetailedService.create(createCommand);
    }

    @Override
    public List<User> list(String userIds) {
        List<Criterion> criterionList = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        if (!CoreStringUtils.isEmpty(userIds)) {
            String[] ids = userIds.split(",");
            for (String userId : ids) {
                integers.add(Integer.valueOf(userId));
            }
            criterionList.add(Restrictions.in("userId", integers));
        }
        return userRepository.list(criterionList, null);
    }

    @Override
    public void updateUser(EditCommand command) {
        User user = searchByUserId(command.getUserId());
        if (null != user) {
            if (0 != command.getDianPao()) {
                user.setDianPao(user.getDianPao() + command.getDianPao());
            }
            if (0 != command.getZimo()) {
                user.setZimo(user.getZimo() + command.getZimo());
            }
            if (0 != command.getGameCount()) {
                user.setGameCount(user.getGameCount() + command.getGameCount());
            }
            userRepository.save(user);
        }
    }

    @Override
    public List<User> ranking(int rankingType) {
        List<Order> orders = new ArrayList<>();
        switch (rankingType) {
            case 0:
                orders.add(Order.desc("dianPao"));
                break;
            case 1:
                orders.add(Order.desc("zimo"));
                break;
            case 2:
                orders.add(Order.desc("gameCount"));
                break;
        }
        return userRepository.list(null, orders, null, null, null, 10);
    }
}
