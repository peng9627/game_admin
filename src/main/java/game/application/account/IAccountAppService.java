package game.application.account;


import game.application.account.command.AuthorizeAccountCommand;
import game.application.account.command.ListAccountCommand;
import game.application.account.command.ResetPasswordCommand;
import game.application.account.representation.AccountRepresentation;
import game.application.auth.command.LoginCommand;
import game.application.shared.command.SharedCommand;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IAccountAppService {

    Pagination<AccountRepresentation> pagination(ListAccountCommand command);

    List<AccountRepresentation> list(ListAccountCommand command);

    AccountRepresentation searchByID(String id);

    AccountRepresentation searchByAccountName(String userName);

    void updateStatus(SharedCommand command);

    void resetPassword(ResetPasswordCommand command);

    void authorized(AuthorizeAccountCommand command);

    AccountRepresentation login(LoginCommand command);

    Pagination<AccountRepresentation> paginationJSON(ListAccountCommand command);

}
