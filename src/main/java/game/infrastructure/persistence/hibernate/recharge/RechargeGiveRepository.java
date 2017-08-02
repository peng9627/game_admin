package game.infrastructure.persistence.hibernate.recharge;

import game.domain.model.recharge.IRechargeGiveRepository;
import game.domain.model.recharge.RechargeGive;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("rechargeGiveRepository")
public class RechargeGiveRepository extends AbstractHibernateGenericRepository<RechargeGive, String>
        implements IRechargeGiveRepository<RechargeGive, String> {

}
