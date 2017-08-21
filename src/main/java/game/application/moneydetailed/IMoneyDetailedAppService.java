package game.application.moneydetailed;

import game.application.moneydetailed.command.CreateMoneyDetailedCommand;
import game.application.moneydetailed.command.ListMoneyDetailedCommand;
import game.application.moneydetailed.representation.MoneyDetailedRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IMoneyDetailedAppService {
    Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command);

    void create(CreateMoneyDetailedCommand command);
}
