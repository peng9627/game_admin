package niuniu.application.auth;

import niuniu.application.account.IAccountAppService;
import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.permission.IPermissionAppService;
import niuniu.application.permission.command.ListPermissionCommand;
import niuniu.application.permission.representation.PermissionRepresentation;
import niuniu.core.enums.EnableStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengyi on 2016/4/5.
 */
@Service("authAppService")
public class AuthAppService implements IAuthAppService {

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private IPermissionAppService permissionAppService;

    @Override
    public AccountRepresentation searchByAccountName(String userName) {
        return accountAppService.searchByAccountName(userName);
    }

    @Override
    public List<PermissionRepresentation> findAllPermission() {
        ListPermissionCommand command = new ListPermissionCommand();
        command.setStatus(EnableStatus.ENABLE);
        return permissionAppService.list(command);
    }

    @Override
    public AccountRepresentation login(LoginCommand command) {
        return accountAppService.login(command);
    }
}
