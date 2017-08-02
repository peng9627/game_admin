package game.application.recharge;

import game.application.recharge.command.CreateRechargeGiveCommand;
import game.core.common.BasicPaginationCommand;
import game.domain.model.recharge.RechargeGive;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IRechargeGiveAppService {

    Pagination<RechargeGive> pagination(BasicPaginationCommand command);

    void create(CreateRechargeGiveCommand command);

    void delete(String id);
}
