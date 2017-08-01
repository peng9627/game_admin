package niuniu.application.recharge;

import niuniu.application.recharge.command.CreateRechargeCommand;
import niuniu.application.recharge.command.ListRechargeCommand;
import niuniu.application.recharge.representation.ApiRechargeRepresentation;
import niuniu.application.recharge.representation.RechargeRepresentation;
import niuniu.domain.model.recharge.Recharge;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
public interface IRechargeAppService {

    Pagination<RechargeRepresentation> pagination(ListRechargeCommand command);

    Pagination<RechargeRepresentation> paginationEq(ListRechargeCommand command);

    List<ApiRechargeRepresentation> list(ListRechargeCommand command);

    BigDecimal totalMoney(ListRechargeCommand command);

    BigDecimal totalMoneyEq(ListRechargeCommand command);

    Recharge pay(CreateRechargeCommand command);
}
