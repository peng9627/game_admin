package game.application.auth;


import game.application.account.representation.AccountRepresentation;
import game.application.auth.command.LoginCommand;
import game.application.permission.representation.PermissionRepresentation;

import java.util.List;

/**
 * Created by pengyi on 2016/4/5.
 */
public interface IAuthAppService {
    AccountRepresentation searchByAccountName(String userName);

    List<PermissionRepresentation> findAllPermission();

    AccountRepresentation login(LoginCommand command);
}
