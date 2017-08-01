package niuniu.domain.service.account;


import niuniu.application.account.command.AuthorizeAccountCommand;
import niuniu.application.account.command.ListAccountCommand;
import niuniu.application.account.command.ResetPasswordCommand;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.core.common.PasswordHelper;
import niuniu.core.enums.EnableStatus;
import niuniu.core.exception.AccountException;
import niuniu.core.exception.NoFoundException;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.account.Account;
import niuniu.domain.model.account.IAccountRepository;
import niuniu.domain.model.role.Role;
import niuniu.domain.model.seal.Seal;
import niuniu.domain.model.user.User;
import niuniu.domain.service.moneydetailed.IMoneyDetailedService;
import niuniu.domain.service.role.IRoleService;
import niuniu.domain.service.seal.ISealService;
import niuniu.domain.service.system.ISystemService;
import niuniu.domain.service.user.IUserService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
@Service("accountService")
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository<Account, String> accountRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISealService sealService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    private ISystemService systemService;

    @Override
    public Pagination<Account> pagination(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return accountRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Account> list(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return accountRepository.list(criterionList, orderList);
    }

    @Override
    public Account searchByID(String id) {
        Account account = accountRepository.getById(id);
        if (null == account) {
            throw new NoFoundException("没有找到ID[" + id + "]的Account数据");
        }
        return account;
    }

    @Override
    public Account searchByAccountName(String userName) {
        return accountRepository.searchByAccountName(userName);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        if (account.getStatus() == EnableStatus.DISABLE) {
            account.changeStatus(EnableStatus.ENABLE);
        } else {
            account.changeStatus(EnableStatus.DISABLE);
        }
        accountRepository.update(account);
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        String password = PasswordHelper.encryptPassword(command.getPassword(), account.getSalt());
        account.changePassword(password);
        accountRepository.update(account);
    }

    @Override
    public void authorized(AuthorizeAccountCommand command) {
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeRoles(roleList);
        accountRepository.update(account);
    }

    @Override
    public Account login(LoginCommand command) {

        if (null != command.getLoginIP()) {
            Seal seal = sealService.bySealNo(command.getLoginIP());
            if (null != seal) {
                throw new AccountException();
            }
        }

        Account account = this.searchByAccountName(command.getUserName());
        User user = (User) account;
        if (null == account) {
            throw new UnknownAccountException();
        }

        if (null != user.getDeviceNo()) {
            Seal seal = sealService.bySealNo(user.getDeviceNo());
            if (null != seal) {
                throw new AccountException();
            }
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                command.getUserName(),
                command.getPassword());
        subject.login(token);

        account.changeLastLoginIP(command.getLoginIP());
        account.changeLastLoginPlatform(command.getLoginPlatform());
        account.changeLastLoginDate(new Date());

        accountRepository.update(account);

        return account;
    }
}
