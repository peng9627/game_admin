package game.domain.service.moneydetailed;

import game.application.moneydetailed.command.CreateMoneyDetailedCommand;
import game.application.moneydetailed.command.ListMoneyDetailedCommand;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IMoneyDetailedService {

    void create(CreateMoneyDetailedCommand command);

    Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command);

}
