package niuniu.domain.service.moneydetailed;

import niuniu.application.moneydetailed.command.CreateMoneyDetailedCommand;
import niuniu.application.moneydetailed.command.ListMoneyDetailedCommand;
import niuniu.domain.model.moneydetailed.MoneyDetailed;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IMoneyDetailedService {

    void create(CreateMoneyDetailedCommand command);

    Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command);

    boolean receiveTask(ListMoneyDetailedCommand command);
}
