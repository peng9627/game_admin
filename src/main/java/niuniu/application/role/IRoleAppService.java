package niuniu.application.role;


import niuniu.application.role.command.CreateRoleCommand;
import niuniu.application.role.command.EditRoleCommand;
import niuniu.application.role.command.ListRoleCommand;
import niuniu.application.role.representation.RoleRepresentation;
import niuniu.application.shared.command.SharedCommand;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IRoleAppService {

    Pagination<RoleRepresentation> paginationJSON(ListRoleCommand command);

    Pagination<RoleRepresentation> pagination(ListRoleCommand command);

    List<RoleRepresentation> list(ListRoleCommand command);

    RoleRepresentation searchByID(String id);

    RoleRepresentation searchByName(String name);

    RoleRepresentation create(CreateRoleCommand command);

    RoleRepresentation edit(EditRoleCommand command);

    void updateStatus(SharedCommand command);
}
