package niuniu.application.logger;

import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.logger.command.ListLoggerCommand;
import niuniu.application.logger.representation.LoggerRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.logger.Logger;
import niuniu.domain.service.logger.ILoggerService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author pengyi
 * Date 17-4-21.
 */
@Service("loggerAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LoggerAppService implements ILoggerAppService {

    private final ILoggerService loggerService;

    private final IMappingService mappingService;

    @Autowired
    public LoggerAppService(ILoggerService loggerService, IMappingService mappingService) {
        this.loggerService = loggerService;
        this.mappingService = mappingService;
    }

    @Override
    public void create(CreateLoggerCommand command) {
        loggerService.create(command);
    }

    @Override
    public Pagination<LoggerRepresentation> pagination(ListLoggerCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);

        Pagination<Logger> pagination = loggerService.pagination(command);
        List<LoggerRepresentation> data = mappingService.mapAsList(pagination.getData(), LoggerRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }
}
