package game.domain.service.recharge;

import game.domain.model.recharge.IRechargeSelectRepository;
import game.domain.model.recharge.RechargeSelect;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("rechargeSelectService")
public class RechargeSelectService implements IRechargeSelectService {

    private final IRechargeSelectRepository<RechargeSelect, String> rechargeSelectRepository;


    @Autowired
    public RechargeSelectService(IRechargeSelectRepository<RechargeSelect, String> rechargeSelectRepository) {
        this.rechargeSelectRepository = rechargeSelectRepository;
    }

    @Override
    public List<RechargeSelect> list() {
        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return rechargeSelectRepository.list(criterionList, orderList, null, null, aliasMap, 100);
    }

    @Override
    public RechargeSelect getById(String id) {
        return rechargeSelectRepository.getById(id);
    }
}
