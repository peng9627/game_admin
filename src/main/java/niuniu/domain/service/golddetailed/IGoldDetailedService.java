package niuniu.domain.service.golddetailed;

import niuniu.application.golddetailed.command.CreateGoldDetailedCommand;
import niuniu.application.golddetailed.command.ListGoldDetailedCommand;
import niuniu.domain.model.golddetailed.GoldDetailed;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IGoldDetailedService {

    void create(CreateGoldDetailedCommand command);

    Pagination<GoldDetailed> pagination(ListGoldDetailedCommand command);

    boolean receiveTask(ListGoldDetailedCommand command);
}
