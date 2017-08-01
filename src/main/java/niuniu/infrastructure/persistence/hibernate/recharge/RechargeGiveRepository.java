package niuniu.infrastructure.persistence.hibernate.recharge;

import niuniu.domain.model.recharge.IRechargeGiveRepository;
import niuniu.domain.model.recharge.RechargeGive;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("rechargeGiveRepository")
public class RechargeGiveRepository extends AbstractHibernateGenericRepository<RechargeGive, String>
        implements IRechargeGiveRepository<RechargeGive, String> {

}
