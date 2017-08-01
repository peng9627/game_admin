package niuniu.domain.service.system;

import niuniu.application.system.command.EditSystemCommand;
import niuniu.domain.model.system.System;

/**
 * Author pengyi
 * Date 16-9-5.
 */
public interface ISystemService {
    System getSystem();

    System edit(EditSystemCommand command);
}
