package niuniu.domain.service.recharge;

import niuniu.application.recharge.command.CreateRechargeGiveCommand;
import niuniu.core.common.BasicPaginationCommand;
import niuniu.domain.model.recharge.RechargeGive;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IRechargeGiveService {

    Pagination<RechargeGive> pagination(BasicPaginationCommand command);

    void create(CreateRechargeGiveCommand command);

    void delete(String id);

    List<RechargeGive> list();
}
