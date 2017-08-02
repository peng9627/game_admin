package game.application.golddetailed;

import game.application.golddetailed.command.CreateGoldDetailedCommand;
import game.application.golddetailed.command.ListGoldDetailedCommand;
import game.application.golddetailed.representation.GoldDetailedRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IGoldDetailedAppService {
    Pagination<GoldDetailedRepresentation> pagination(ListGoldDetailedCommand command);

    boolean receiveTask(ListGoldDetailedCommand command);

    void create(CreateGoldDetailedCommand command);
}
