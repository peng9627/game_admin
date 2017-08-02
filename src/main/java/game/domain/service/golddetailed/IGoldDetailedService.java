package game.domain.service.golddetailed;

import game.application.golddetailed.command.CreateGoldDetailedCommand;
import game.application.golddetailed.command.ListGoldDetailedCommand;
import game.domain.model.golddetailed.GoldDetailed;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IGoldDetailedService {

    void create(CreateGoldDetailedCommand command);

    Pagination<GoldDetailed> pagination(ListGoldDetailedCommand command);

    boolean receiveTask(ListGoldDetailedCommand command);
}
