package niuniu.domain.service.logger;

import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.logger.command.ListLoggerCommand;
import niuniu.domain.model.logger.Logger;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public interface ILoggerService {
    void create(CreateLoggerCommand command);

    Pagination<Logger> pagination(ListLoggerCommand command);
}
