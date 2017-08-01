package niuniu.domain.service.seal;

import niuniu.application.seal.command.CreateSealCommand;
import niuniu.application.seal.command.ListSealCommand;
import niuniu.core.enums.SealType;
import niuniu.core.exception.ExistException;
import niuniu.core.exception.NoFoundException;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.seal.ISealRepository;
import niuniu.domain.model.seal.Seal;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author pengyi
 * Date 2016/11/30.
 */
@Service("sealService")
public class SealService implements ISealService {

    private final ISealRepository<Seal, String> sealRepository;

    @Autowired
    public SealService(ISealRepository<Seal, String> sealRepository) {
        this.sealRepository = sealRepository;
    }

    @Override
    public Seal bySealNo(String seatNo) {
        return sealRepository.bySealNo(seatNo);
    }

    @Override
    public Pagination<Seal> pagination(ListSealCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getSealNo())) {
            criterionList.add(Restrictions.like("sealNo", command.getSealNo(), MatchMode.ANYWHERE));
        }
        if (null != command.getType() && command.getType() != SealType.ALL) {
            criterionList.add(Restrictions.eq("type", command.getType()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return sealRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public void create(CreateSealCommand command) {
        Seal seal = sealRepository.bySealNo(command.getSealNo());
        if (null != seal) {
            throw new ExistException();
        }
        Seal seal1 = new Seal(command.getType(), command.getSealNo());
        sealRepository.save(seal1);
    }

    @Override
    public void delete(String id) {
        Seal seal = sealRepository.getById(id);
        if (null == seal) {
            throw new NoFoundException();
        }
        sealRepository.delete(seal);
    }


}
