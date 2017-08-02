package game.domain.service.account;


import game.application.account.command.AuthorizeAccountCommand;
import game.application.account.command.ListAccountCommand;
import game.application.account.command.ResetPasswordCommand;
import game.application.auth.command.LoginCommand;
import game.application.shared.command.SharedCommand;
import game.domain.model.account.Account;
import game.infrastructure.persistence.hibernate.generic.Pagination;

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
