package game.domain.service.integraldetailed;

import game.application.integraldetailed.command.CreateCommand;
import game.application.integraldetailed.command.ListCommand;
import game.domain.model.integraldetailed.IntegralDetailed;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IIntegralDetailedService {

    void create(CreateCommand command);

    Pagination<IntegralDetailed> pagination(ListCommand command);

}
