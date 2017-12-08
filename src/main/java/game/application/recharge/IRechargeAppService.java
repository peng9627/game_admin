package game.application.recharge;


import game.application.recharge.command.CreateRechargeCommand;
import game.application.recharge.command.ListRechargeCommand;
import game.application.recharge.representation.ApiRechargeRepresentation;
import game.application.recharge.representation.RechargeRepresentation;
import game.core.pay.wechat.WechatNotify;
import game.domain.model.recharge.Recharge;
import game.infrastructure.persistence.hibernate.generic.Pagination;

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

    Recharge recharge(CreateRechargeCommand command);

    void wechatSuccess(WechatNotify notify);
}
