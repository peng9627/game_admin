package game.domain.service.recharge;


import game.domain.model.recharge.RechargeSelect;

import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
public interface IRechargeSelectService {

    List<RechargeSelect> list();

    RechargeSelect getById(String id);
}
