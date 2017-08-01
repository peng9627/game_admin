package niuniu.application.system;

import niuniu.application.system.command.EditSystemCommand;
import niuniu.application.system.representation.ApiSystemRepresentation;
import niuniu.domain.model.system.System;

/**
 * Author pengyi
 * Date 16-9-5.
 */
public interface ISystemAppService {
    System getSystem();

    ApiSystemRepresentation getApiSystem();

    System edit(EditSystemCommand command);
}
