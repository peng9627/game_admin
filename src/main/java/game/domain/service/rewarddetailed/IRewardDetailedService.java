package game.domain.service.rewarddetailed;

import game.application.rewarddetailed.command.CreateCommand;
import game.application.rewarddetailed.command.ListCommand;
import game.domain.model.rewarddetailed.RewardDetailed;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IRewardDetailedService {

    void create(CreateCommand command);

    Pagination<RewardDetailed> pagination(ListCommand command);

}
