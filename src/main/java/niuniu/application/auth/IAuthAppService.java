package niuniu.application.auth;


import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.permission.representation.PermissionRepresentation;

import java.util.List;

/**
 * Created by pengyi on 2016/4/5.
 */
public interface IAuthAppService {
    AccountRepresentation searchByAccountName(String userName);

    List<PermissionRepresentation> findAllPermission();

    AccountRepresentation login(LoginCommand command);
}
