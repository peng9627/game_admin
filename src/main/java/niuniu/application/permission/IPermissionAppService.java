package niuniu.application.permission;


import niuniu.application.permission.command.CreatePermissionCommand;
import niuniu.application.permission.command.EditPermissionCommand;
import niuniu.application.permission.command.ListPermissionCommand;
import niuniu.application.permission.representation.PermissionRepresentation;
import niuniu.application.shared.command.SharedCommand;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IPermissionAppService {

    Pagination<PermissionRepresentation> pagination(ListPermissionCommand command);

    Pagination<PermissionRepresentation> paginationJSON(ListPermissionCommand command);

    List<PermissionRepresentation> list(ListPermissionCommand command);

    PermissionRepresentation searchByID(String id);

    PermissionRepresentation searchByName(String name);

    PermissionRepresentation create(CreatePermissionCommand command);

    PermissionRepresentation edit(EditPermissionCommand command);

    void updateStatus(SharedCommand command);

}
