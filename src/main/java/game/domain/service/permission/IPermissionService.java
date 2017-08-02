package game.domain.service.permission;


import game.application.permission.command.CreatePermissionCommand;
import game.application.permission.command.EditPermissionCommand;
import game.application.permission.command.ListPermissionCommand;
import game.application.shared.command.SharedCommand;
import game.domain.model.permission.Permission;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IPermissionService {

    Pagination<Permission> pagination(ListPermissionCommand command);

    List<Permission> list(ListPermissionCommand command);

    List<Permission> searchByIDs(List<String> ids);

    Permission searchByID(String id);

    Permission searchByName(String name);

    Permission create(CreatePermissionCommand command);

    Permission edit(EditPermissionCommand command);

    void updateStatus(SharedCommand command);
}
