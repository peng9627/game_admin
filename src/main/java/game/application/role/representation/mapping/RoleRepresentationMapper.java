package game.application.role.representation.mapping;

import game.application.permission.representation.PermissionRepresentation;
import game.application.role.representation.RoleRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.role.Role;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
@Component
public class RoleRepresentationMapper extends CustomMapper<Role, RoleRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Role role, RoleRepresentation representation, MappingContext context) {
        if (null != role.getPermissions()) {
            List<PermissionRepresentation> data = mappingService.mapAsList(role.getPermissions(), PermissionRepresentation.class);
            representation.setPermissions(data);
        }
    }
}
