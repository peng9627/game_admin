package game.application.rewarddetailed;

import game.application.rewarddetailed.command.CreateCommand;
import game.application.rewarddetailed.command.ListCommand;
import game.application.rewarddetailed.representation.RewardDetailedRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IRewardDetailedAppService {
    Pagination<RewardDetailedRepresentation> pagination(ListCommand command);

    void create(CreateCommand command);
}
