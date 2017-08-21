package game.domain.service.moneydetailed;

import game.application.moneydetailed.command.CreateCommand;
import game.application.moneydetailed.command.ListCommand;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IMoneyDetailedService {

    void create(CreateCommand command);

    Pagination<MoneyDetailed> pagination(ListCommand command);

}
