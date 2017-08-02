package game.infrastructure.persistence.hibernate.moneydetailed;

import game.domain.model.moneydetailed.IMoneyDetailedRepository;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("moneyDetailedRepository")
public class MoneyDetailedRepository extends AbstractHibernateGenericRepository<MoneyDetailed, String>
        implements IMoneyDetailedRepository<MoneyDetailed, String> {
}
