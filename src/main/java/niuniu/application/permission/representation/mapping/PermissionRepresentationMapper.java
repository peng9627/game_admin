package niuniu.application.permission.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import niuniu.application.permission.representation.PermissionRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.permission.Permission;
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
