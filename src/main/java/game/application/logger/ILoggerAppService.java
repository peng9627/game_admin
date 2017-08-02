package game.application.logger;

import game.application.logger.command.CreateLoggerCommand;
import game.application.logger.command.ListLoggerCommand;
import game.application.logger.representation.LoggerRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public interface ILoggerAppService {
    void create(CreateLoggerCommand command);

    Pagination<LoggerRepresentation> pagination(ListLoggerCommand command);
}
