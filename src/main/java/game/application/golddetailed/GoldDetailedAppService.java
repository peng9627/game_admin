package game.application.golddetailed;

import game.application.golddetailed.command.CreateGoldDetailedCommand;
import game.application.golddetailed.command.ListGoldDetailedCommand;
import game.application.golddetailed.representation.GoldDetailedRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.golddetailed.GoldDetailed;
import game.domain.service.golddetailed.IGoldDetailedService;
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
@Service("goldDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GoldDetailedAppService implements IGoldDetailedAppService {

    private final IGoldDetailedService goldDetailedService;

    private final IMappingService mappingService;

    @Autowired
    public GoldDetailedAppService(IGoldDetailedService goldDetailedService, IMappingService mappingService) {
        this.goldDetailedService = goldDetailedService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<GoldDetailedRepresentation> pagination(ListGoldDetailedCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<GoldDetailed> pagination = goldDetailedService.pagination(command);
        List<GoldDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), GoldDetailedRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public boolean receiveTask(ListGoldDetailedCommand command) {
        return goldDetailedService.receiveTask(command);
    }

    @Override
    public void create(CreateGoldDetailedCommand command) {
        goldDetailedService.create(command);
    }
}