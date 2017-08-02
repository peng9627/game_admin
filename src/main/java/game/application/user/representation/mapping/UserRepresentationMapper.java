package game.application.user.representation.mapping;

import game.application.user.representation.UserRepresentation;
import game.core.enums.EnableStatus;
import game.domain.model.permission.Permission;
import game.domain.model.role.Role;
import game.domain.model.user.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by pengyi on 2016/4/19.
 */
@Component
public class UserRepresentationMapper extends CustomMapper<User, UserRepresentation> {

    public void mapAtoB(User user, UserRepresentation representation, MappingContext context) {
        if (null != user.getParent()) {
            representation.setParent(user.getParent().getUserName());
        }
        if (null == representation.getRoles()) {
            representation.setRoles(new ArrayList<>());
        } else {
            representation.getRoles().clear();
        }
        if (null == representation.getPermissions()) {
            representation.setPermissions(new ArrayList<>());
        } else {
            representation.getPermissions().clear();
        }
        if (null != user.getRoles()) {
            for (Role role : user.getRoles()) {
                representation.getRoles().add(role.getName());
                if (role.getStatus().compareTo(EnableStatus.ENABLE) == 0) {
                    for (Permission permission : role.getPermissions()) {
                        if (permission.getStatus().compareTo(EnableStatus.ENABLE) == 0) {
                            representation.getPermissions().add(permission.getName());
                        }
                    }
                }
            }
        }
    }

}
