package niuniu.application.logger;

import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.logger.command.ListLoggerCommand;
import niuniu.application.logger.representation.LoggerRepresentation;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public interface ILoggerAppService {
    void create(CreateLoggerCommand command);

    Pagination<LoggerRepresentation> pagination(ListLoggerCommand command);
}
