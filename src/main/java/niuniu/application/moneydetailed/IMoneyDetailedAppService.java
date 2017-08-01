package niuniu.application.moneydetailed;

import niuniu.application.moneydetailed.command.CreateMoneyDetailedCommand;
import niuniu.application.moneydetailed.command.ListMoneyDetailedCommand;
import niuniu.application.moneydetailed.representation.MoneyDetailedRepresentation;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IMoneyDetailedAppService {
    Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command);

    boolean receiveTask(ListMoneyDetailedCommand command);

    void create(CreateMoneyDetailedCommand command);
}
