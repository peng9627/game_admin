package niuniu.domain.service.permission;


import niuniu.application.permission.command.CreatePermissionCommand;
import niuniu.application.permission.command.EditPermissionCommand;
import niuniu.application.permission.command.ListPermissionCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.domain.model.permission.Permission;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

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
