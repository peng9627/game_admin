package niuniu.application.system;

import niuniu.application.system.command.EditSystemCommand;
import niuniu.application.system.representation.ApiSystemRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.system.System;
import niuniu.domain.service.system.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author pengyi
 * Date 16-9-5.
 */
@Service("systemAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemAppService implements ISystemAppService {

    private final ISystemService systemService;

    private final IMappingService mappingService;

    @Autowired
    public SystemAppService(ISystemService systemService, IMappingService mappingService) {
        this.systemService = systemService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public System getSystem() {

        return systemService.getSystem();
    }

    @Override
    public ApiSystemRepresentation getApiSystem() {
        return mappingService.map(systemService.getSystem(), ApiSystemRepresentation.class, false);
    }

    @Override
    public System edit(EditSystemCommand command) {
        return systemService.edit(command);
    }
}
