package game.application.system;

import game.application.system.representation.SystemRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.system.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Service("systemAppService")
public class SystemAppService implements ISystemAppService {

    private final ISystemService systemService;

    private final IMappingService mappingService;

    @Autowired
    public SystemAppService(ISystemService systemService, IMappingService mappingService) {
        this.systemService = systemService;
        this.mappingService = mappingService;
    }

    @Override
    public SystemRepresentation info() {
        return mappingService.map(systemService.info(), SystemRepresentation.class, false);
    }
}
