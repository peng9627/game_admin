package game.domain.service.system;

import game.application.system.command.EditSystemCommand;
import game.domain.model.system.System;

/**
 * Author pengyi
 * Date 16-9-5.
 */
public interface ISystemService {
    System getSystem();

    System edit(EditSystemCommand command);
}
