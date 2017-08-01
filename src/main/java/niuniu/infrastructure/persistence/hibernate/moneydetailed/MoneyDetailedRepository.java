package niuniu.infrastructure.persistence.hibernate.moneydetailed;

import niuniu.domain.model.moneydetailed.IMoneyDetailedRepository;
import niuniu.domain.model.moneydetailed.MoneyDetailed;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("moneyDetailedRepository")
public class MoneyDetailedRepository extends AbstractHibernateGenericRepository<MoneyDetailed, String>
        implements IMoneyDetailedRepository<MoneyDetailed, String> {
}
