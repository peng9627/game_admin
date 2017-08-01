package niuniu.domain.service.user;

import niuniu.application.auth.command.LoginCommand;
import niuniu.application.moneydetailed.command.CreateMoneyDetailedCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.application.user.command.*;
import niuniu.core.common.PasswordHelper;
import niuniu.core.enums.EnableStatus;
import niuniu.core.enums.FlowType;
import niuniu.core.enums.Sex;
import niuniu.core.exception.*;
import niuniu.core.util.CoreDateUtils;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.role.Role;
import niuniu.domain.model.seal.Seal;
import niuniu.domain.model.system.System;
import niuniu.domain.model.user.IUserRepository;
import niuniu.domain.model.user.User;
import niuniu.domain.service.account.IAccountService;
import niuniu.domain.service.moneydetailed.IMoneyDetailedService;
import niuniu.domain.service.role.IRoleService;
import niuniu.domain.service.seal.ISealService;
import niuniu.domain.service.system.ISystemService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.apache.shiro.authc.UnknownAccountException;
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
 * Date : 2016/4/19.
 */
@Service("userService")
public class UserService implements IUserService {

    private final IUserRepository<User, String> userRepository;

    @Autowired
    private IAccountService accountService;

    private final IRoleService roleService;

    private final ISealService sealService;

    private final ISystemService systemService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    public UserService(ISystemService systemService, IRoleService roleService, IUserRepository<User, String> userRepository, ISealService sealService) {
        this.systemService = systemService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.sealService = sealService;
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
    public User searchByName(String userName) {
        return userRepository.searchByName(userName);
    }

    @Override
    public boolean checkDeviceNo(String deviceNo) {

        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("deviceNo", deviceNo.trim()));
        return userRepository.list(criterionList, null).size() < 2;
    }

    @Override
    public User searchByName(String userName, LockMode lockMode) {
        return userRepository.searchByName(userName, lockMode);
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
    public User create(CreateUserCommand command) {
        User parent = null;
        if (!CoreStringUtils.isEmpty(command.getParent())) {
            parent = this.searchByID(command.getParent());
        }
        List<Role> roleList;
        if (null == command.getRoles() || command.getRoles().size() == 0) {
            roleList = new ArrayList<>();
            roleList.add(roleService.searchByName("user"));
        } else {
            roleList = roleService.searchByIDs(command.getRoles());
        }
        if (null != accountService.searchByAccountName(command.getUserName())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }

        System system = systemService.getSystem();
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);

        User user = new User("", null, Sex.MAN, command.getUserName(), password, salt, null, null, null, roleList, EnableStatus.ENABLE,
                command.getName(), null);
        user.changeParent(parent);
        userRepository.save(user);

        //创建资金明细
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setDescription("注册送" + system.getRegisterGive());
        moneyDetailedCommand.setFlowType(FlowType.IN_FLOW);
        moneyDetailedCommand.setMoney(system.getRegisterGive());
        moneyDetailedCommand.setUserName(user.getUserName());
        moneyDetailedService.create(moneyDetailedCommand);

        return user;
    }

    @Override
    public User edit(EditUserCommand command) {
        User user = this.searchByID(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());
        user.changeName(command.getName());
        user.changePhoneNo(command.getPhoneNo());
        userRepository.update(user);
        return user;
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
    public void addMoney(MoneyCommand command) {
        User user = this.searchByID(command.getId());

        //创建资金明细
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setDescription(command.getDescribe());
        moneyDetailedCommand.setFlowType(FlowType.IN_FLOW);
        moneyDetailedCommand.setMoney(command.getMoney());
        moneyDetailedCommand.setUserName(user.getUserName());
        moneyDetailedService.create(moneyDetailedCommand);
    }

    @Override
    public void subtractMoney(MoneyCommand command) {
        User user = this.searchByID(command.getId());

        //创建资金明细
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setDescription(command.getDescribe());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setMoney(command.getMoney());
        moneyDetailedCommand.setUserName(user.getUserName());
        moneyDetailedService.create(moneyDetailedCommand);
    }

    @Override
    public void updateVip(SharedCommand command) {
        User user = this.searchByID(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());
        if (user.getVip()) {
            user.changeVip(false);
        } else {
            user.changeVip(true);
        }
        userRepository.update(user);
    }

    @Override
    public void updateRanking() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("money"));
        List<User> userList = userRepository.list(null, orderList);
        for (int i = userList.size() - 1; i >= 0; i--) {
            userList.get(i).changeRanking(i + 1);
            userRepository.update(userList.get(i));
        }
    }

    @Override
    public BigDecimal totalMoney(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }

        return userRepository.totalMoney(criterionList);
    }

    @Override
    public void spreadGet(String id) {
        User user = searchByID(id);
        CreateMoneyDetailedCommand moneyCommand = new CreateMoneyDetailedCommand();
        moneyCommand.setDescription("领取推荐奖励");
        moneyCommand.setFlowType(FlowType.IN_FLOW);
        moneyCommand.setMoney(user.getSpreadCanGet());
        moneyCommand.setUserName(user.getUserName());
        moneyDetailedService.create(moneyCommand);

        user.changeSpreadGetted(user.getSpreadGetted().add(user.getSpreadCanGet()));
        user.changeSpreadCanGet(BigDecimal.ZERO);
        userRepository.save(user);
    }

    @Override
    public User login(LoginCommand command) {
        if (null != command.getLoginIP()) {
            Seal seal = sealService.bySealNo(command.getLoginIP());
            if (null != seal) {
                throw new AccountException();
            }
        }

        User user = userRepository.searchByName(command.getUserName());
        if (null == user) {
            throw new UnknownAccountException();
        }

        if (null != user.getDeviceNo()) {
            Seal seal = sealService.bySealNo(user.getDeviceNo());
            if (null != seal) {
                throw new AccountException();
            }
        }

        user.changeLastLoginIP(command.getLoginIP());
        user.changeLastLoginPlatform(command.getLoginPlatform());
        user.changeLastLoginDate(new Date());

        userRepository.update(user);
        return user;
    }

    /**
     * 微信登陆
     *
     * @param command 传入参数
     * @return user
     */
    @Override
    public User weChatLogin(LoginCommand command) {

        User user = userRepository.searchByToken(command.getToken());
        if (user != null) { //非首次登陆
            if (command.getLoginIP() != null && !user.getLastLoginIP().equals(command.getLoginIP())) {
                user.changeLastLoginIP(command.getLoginIP());
            }
            if (command.getLoginPlatform() != null && !user.getLastLoginPlatform().equals(command.getLoginPlatform())) {
                user.changeLastLoginPlatform(command.getLoginPlatform());
            }
            if (command.getHeadimgurl() != null && !user.getHead().equals(command.getHeadimgurl())) {
                user.changeHead(command.getHeadimgurl());
            }
            if (command.getSex() != null && !user.getSex().equals(command.getSex())) {
                user.changeSex(command.getSex());
            }
            if (command.getSex() != null && !user.getName().equals(command.getName())) {
                user.changeName(command.getName());
            }
            //是否连续登陆
            Date date = new Date();
            if (!CoreDateUtils.isSameDay(date, CoreDateUtils.addDay(user.getLastLoginDate(), 1)) && !CoreDateUtils.isSameDay(date, user.getLastLoginDate())) {//没有连续登陆，重置登陆天数
                user.setDays(0);
                user.setReward(1);//重置连续登陆奖励次数
                user.setBenefit(3);//重置补助领取次数
            } else if (CoreDateUtils.isSameDay(date, CoreDateUtils.addDay(user.getLastLoginDate(), 1))) { //连续登陆
                user.setReward(1);//重置连续登陆奖励次数
                user.setBenefit(3);//重置补助领取次数
            }
            user.changeLastLoginDate(date);

            userRepository.update(user);

        } else { //首次登陆
            String userName = null;
            while (userName == null) {
                Random random = new Random();
                int number = random.nextInt(89999999) + 10000000;
                if (null == accountService.searchByAccountName("" + number)) {
                    userName = "" + number;
                }
            }

            String salt = PasswordHelper.getSalt();
            String password = PasswordHelper.encryptPassword("123456", salt);
            List<Role> roleList = new ArrayList<>();
            roleList.add(roleService.searchByName("user"));
            user = new User(command.getToken(), command.getHeadimgurl(), command.getSex(), userName, password, salt, command.getLoginIP(), new Date(), command.getLoginPlatform(), roleList, EnableStatus.ENABLE,
                    command.getName(), null);

            user.changeStatus(EnableStatus.ENABLE);//默认启用
            user.setCreateDate(new Date());
            user.setDays(1);
            //首次登陆送1W金币
            user.setReward(1);
            user.setBenefit(3);
            userRepository.save(user);

            System system = systemService.getSystem();
            //创建资金明细
            CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
            moneyDetailedCommand.setDescription("注册送" + system.getRegisterGive());
            moneyDetailedCommand.setFlowType(FlowType.IN_FLOW);
            moneyDetailedCommand.setMoney(system.getRegisterGive());
            moneyDetailedCommand.setUserName(user.getUserName());
            moneyDetailedService.create(moneyDetailedCommand);
        }
        return user;
    }


    @Override
    public void bindInviteCode(InviteCodeCommand command) {

        if (!CoreStringUtils.isEmpty(command.getUserName()) && !CoreStringUtils.isEmpty(command.getInviteCode())) {
            User user = userRepository.searchByName(command.getUserName());
            if (user != null) {
                if (user.getInviteCode() != null) {//不能重复绑定邀请码
                    throw new InviteCodeException();
                }
                List<Criterion> criterionList = new ArrayList<>();
                criterionList.add(Restrictions.eq("inviteCode", command.getInviteCode()));
                Pagination<User> pagination = userRepository.pagination(1, 1, criterionList, null);
                if (pagination.getData().size() > 0) { //邀请码已存在
                    throw new ExistException();
                }
                user.setInviteCode(command.getInviteCode());
                userRepository.update(user);
            } else {
                throw new AccountException();
            }
        } else {
            throw new AccountException();
        }
    }

    @Override
    public String searchIdByToken(String token) {

        return userRepository.searchByToken(token).getId();
    }

    /**
     * 连续登陆领取金币
     *
     * @param userName 用户名
     * @return 可领取金币数
     */
    @Override
    public BigDecimal receiveGold(String userName) {

        User user = userRepository.searchByName(userName);
        if (user != null && user.getReward() == 1) { //有可领取奖励次数
            BigDecimal bigDecimal;
            //可领取金币数为：(连续登陆的天数-1)*500+2000，7天为最大上限，超过7天按7天计算
            if (user.getDays() > 6) {
                bigDecimal = BigDecimal.valueOf(5000);
            } else {
                bigDecimal = BigDecimal.valueOf(2000).add(BigDecimal.valueOf(500).multiply(BigDecimal.valueOf(user.getDays())));
            }
            user.setDays(user.getDays() + 1);
            user.setReward(0); //成功领取后当天不能再领取
            user.setGold(bigDecimal.add(user.getGold()));//领取成功更新金币余额
            userRepository.update(user);
            return bigDecimal;
        } else {
            throw new CountNotEnoughException();
        }
    }

    /**
     * 领取救济金
     *
     * @param userName 用户名
     * @return 剩余可领取次数
     */
    @Override
    public BigDecimal receiveBenefit(String userName) {

        User user = userRepository.searchByName(userName);
        if (user != null && user.getGold().compareTo(BigDecimal.valueOf(3000)) > 0 && user.getBenefit() > 0) { //剩余次数大于0,可领取
            user.setBenefit(user.getBenefit() - 1);
            BigDecimal bigDecimal = BigDecimal.valueOf(5000);
            user.setGold(bigDecimal.add(user.getGold()));//领取成功更新金币余额
            userRepository.update(user);
            return bigDecimal;
        } else {
            throw new CountNotEnoughException();
        }
    }
}
