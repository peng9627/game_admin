package niuniu.application.golddetailed;

import niuniu.application.golddetailed.command.CreateGoldDetailedCommand;
import niuniu.application.golddetailed.command.ListGoldDetailedCommand;
import niuniu.application.golddetailed.representation.GoldDetailedRepresentation;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IGoldDetailedAppService {
    Pagination<GoldDetailedRepresentation> pagination(ListGoldDetailedCommand command);

    boolean receiveTask(ListGoldDetailedCommand command);

    void create(CreateGoldDetailedCommand command);
}
