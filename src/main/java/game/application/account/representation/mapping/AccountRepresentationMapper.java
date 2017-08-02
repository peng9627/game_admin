package game.application.account.representation.mapping;

import game.application.account.representation.AccountRepresentation;
import game.core.enums.EnableStatus;
import game.domain.model.account.Account;
import game.domain.model.permission.Permission;
import game.domain.model.role.Role;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
@Component
public class AccountRepresentationMapper extends CustomMapper<Account, AccountRepresentation> {

    public void mapAtoB(Account account, AccountRepresentation representation, MappingContext context) {
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
        if (null != account.getRoles()) {
            for (Role role : account.getRoles()) {
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
