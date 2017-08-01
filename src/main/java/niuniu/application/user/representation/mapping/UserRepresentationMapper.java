package niuniu.application.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import niuniu.application.user.representation.UserRepresentation;
import niuniu.core.enums.EnableStatus;
import niuniu.domain.model.permission.Permission;
import niuniu.domain.model.role.Role;
import niuniu.domain.model.user.User;
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
