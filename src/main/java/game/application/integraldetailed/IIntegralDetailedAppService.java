package game.application.integraldetailed;

import game.application.integraldetailed.command.CreateCommand;
import game.application.integraldetailed.command.ListCommand;
import game.application.integraldetailed.representation.IntegralDetailedRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IIntegralDetailedAppService {
    Pagination<IntegralDetailedRepresentation> pagination(ListCommand command);

    void create(CreateCommand command);
}
