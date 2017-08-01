package niuniu.domain.service.role;

import niuniu.application.role.command.CreateRoleCommand;
import niuniu.application.role.command.EditRoleCommand;
import niuniu.application.role.command.ListRoleCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.domain.model.role.Role;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface IRoleService {

    Pagination<Role> pagination(ListRoleCommand command);

    List<Role> list(ListRoleCommand command);

    Role searchByID(String id);

    Role searchByName(String name);

    Role create(CreateRoleCommand command);

    Role edit(EditRoleCommand command);

    void updateStatus(SharedCommand command);

    List<Role> searchByIDs(List<String> ids);
}
