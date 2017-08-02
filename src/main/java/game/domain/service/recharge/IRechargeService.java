package game.domain.service.recharge;

import game.application.recharge.command.CreateRechargeCommand;
import game.application.recharge.command.ListRechargeCommand;
import game.domain.model.recharge.Recharge;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IRechargeService {
    Pagination<Recharge> pagination(ListRechargeCommand command);

    List<Recharge> list(ListRechargeCommand command);

    Recharge pay(CreateRechargeCommand command);

    Recharge getById(String agent_bill_id);

    BigDecimal totalMoney(ListRechargeCommand command);

    BigDecimal totalMoneyEq(ListRechargeCommand command);

    boolean payNotify(String payid, String orderNo, String amount);

    Pagination<Recharge> paginationEq(ListRechargeCommand command);
}
