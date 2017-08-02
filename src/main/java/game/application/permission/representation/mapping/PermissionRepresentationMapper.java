package game.application.permission.representation.mapping;

import game.application.permission.representation.PermissionRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.permission.Permission;
import ma.glasnost.orika.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
@Component
public class PermissionRepresentationMapper extends CustomMapper<Permission, PermissionRepresentation> {

    @Autowired
    private IMappingService mappingService;
}
