package game.domain.service.logger;

import game.application.logger.command.CreateLoggerCommand;
import game.application.logger.command.ListLoggerCommand;
import game.domain.model.logger.Logger;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public interface ILoggerService {
    void create(CreateLoggerCommand command);

    Pagination<Logger> pagination(ListLoggerCommand command);
}
