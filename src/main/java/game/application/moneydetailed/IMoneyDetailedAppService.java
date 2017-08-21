package game.application.moneydetailed;

import game.application.moneydetailed.command.CreateCommand;
import game.application.moneydetailed.command.ListCommand;
import game.application.moneydetailed.representation.MoneyDetailedRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IMoneyDetailedAppService {
    Pagination<MoneyDetailedRepresentation> pagination(ListCommand command);

    void create(CreateCommand command);
}
