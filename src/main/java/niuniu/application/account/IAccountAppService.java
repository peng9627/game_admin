package niuniu.application.account;


import niuniu.application.account.command.AuthorizeAccountCommand;
import niuniu.application.account.command.ListAccountCommand;
import niuniu.application.account.command.ResetPasswordCommand;
import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

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
