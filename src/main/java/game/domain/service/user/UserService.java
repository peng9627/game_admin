package game.domain.service.user;

import game.application.user.command.ListUserCommand;
import game.application.user.command.LoginCommand;
import game.core.enums.EnableStatus;
import game.core.exception.NoFoundException;
import game.core.util.CoreStringUtils;
import game.domain.model.user.IUserRepository;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
@Service("userService")
public class UserService implements IUserService {

    private final IUserRepository<User, String> userRepository;

    @Autowired
    public UserService(IUserRepository<User, String> userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Pagination<User> pagination(ListUserCommand command) {
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
        }
        user.setAgent(command.getAgent());
        user.setArea(command.getArea());
        user.setHead(command.getHead());
        user.setLastLoginDate(new Date());
        user.setLastLoginIp(command.getIp());
        user.setNickname(command.getNickname());
        user.setSex(command.getSex());

        userRepository.save(user);
        return user;
    }
}
