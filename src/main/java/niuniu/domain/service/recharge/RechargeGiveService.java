package niuniu.domain.service.recharge;

import niuniu.application.recharge.command.CreateRechargeGiveCommand;
import niuniu.core.common.BasicPaginationCommand;
import niuniu.domain.model.recharge.IRechargeGiveRepository;
import niuniu.domain.model.recharge.RechargeGive;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-9.
 */
@Service("rechargeGiveService")
public class RechargeGiveService implements IRechargeGiveService {

    private final IRechargeGiveRepository<RechargeGive, String> rechargeGiveRepository;

    @Autowired
    public RechargeGiveService(IRechargeGiveRepository<RechargeGive, String> rechargeGiveRepository) {
        this.rechargeGiveRepository = rechargeGiveRepository;
    }

    @Override
    public Pagination<RechargeGive> pagination(BasicPaginationCommand command) {
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("money"));
        return rechargeGiveRepository.pagination(command.getPage(), command.getPageSize(), null, orderList);
    }

    @Override
    public void create(CreateRechargeGiveCommand command) {
        RechargeGive rechargeGive = new RechargeGive(command.getMoney(), command.getGiveMoney());
        rechargeGiveRepository.save(rechargeGive);
    }

    @Override
    public void delete(String id) {
        RechargeGive rechargeGive = rechargeGiveRepository.getById(id);
        rechargeGiveRepository.delete(rechargeGive);
    }

    @Override
    public List<RechargeGive> list() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("money"));
        return rechargeGiveRepository.list(null, orderList);
    }
}
