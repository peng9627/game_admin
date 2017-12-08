package game.application.rewarddetailed;

import game.application.rewarddetailed.command.CreateCommand;
import game.application.rewarddetailed.command.ListCommand;
import game.application.rewarddetailed.representation.RewardDetailedRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.rewarddetailed.RewardDetailed;
import game.domain.service.rewarddetailed.IRewardDetailedService;
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
@Service("rewardDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RewardDetailedAppService implements IRewardDetailedAppService {

    private final IRewardDetailedService rewardDetailedService;

    private final IMappingService mappingService;

    @Autowired
    public RewardDetailedAppService(IRewardDetailedService rewardDetailedService, IMappingService mappingService) {
        this.rewardDetailedService = rewardDetailedService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<RewardDetailedRepresentation> pagination(ListCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<RewardDetailed> pagination = rewardDetailedService.pagination(command);
        List<RewardDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), RewardDetailedRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public void create(CreateCommand command) {
        rewardDetailedService.create(command);
    }
}
