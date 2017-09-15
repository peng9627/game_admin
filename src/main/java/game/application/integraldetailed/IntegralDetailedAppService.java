package game.application.integraldetailed;

import game.application.integraldetailed.command.CreateCommand;
import game.application.integraldetailed.command.ListCommand;
import game.application.integraldetailed.representation.IntegralDetailedRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.integraldetailed.IntegralDetailed;
import game.domain.service.integraldetailed.IIntegralDetailedService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
@Service("integralDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IntegralDetailedAppService implements IIntegralDetailedAppService {

    private final IIntegralDetailedService integralDetailedService;

    private final IMappingService mappingService;

    @Autowired
    public IntegralDetailedAppService(IIntegralDetailedService integralDetailedService, IMappingService mappingService) {
        this.integralDetailedService = integralDetailedService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<IntegralDetailedRepresentation> pagination(ListCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<IntegralDetailed> pagination = integralDetailedService.pagination(command);
        List<IntegralDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), IntegralDetailedRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public void create(CreateCommand command) {
        integralDetailedService.create(command);
    }
}
