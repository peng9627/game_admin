package game.application.system;

import game.application.system.command.EditSystemCommand;
import game.application.system.representation.ApiSystemRepresentation;
import game.domain.model.system.System;

/**
 * Author pengyi
 * Date 16-9-5.
 */
public interface ISystemAppService {
    System getSystem();

    ApiSystemRepresentation getApiSystem();

    System edit(EditSystemCommand command);
}
