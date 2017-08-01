package niuniu.domain.service.account;


import niuniu.application.account.command.AuthorizeAccountCommand;
import niuniu.application.account.command.ListAccountCommand;
import niuniu.application.account.command.ResetPasswordCommand;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.domain.model.account.Account;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IAccountService {

    Pagination<Account> pagination(ListAccountCommand command);

    List<Account> list(ListAccountCommand command);

    Account searchByID(String id);

    Account searchByAccountName(String userName);

    void updateStatus(SharedCommand command);

    void resetPassword(ResetPasswordCommand command);

    void authorized(AuthorizeAccountCommand command);

    Account login(LoginCommand command);

}
