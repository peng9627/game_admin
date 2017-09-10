package game.infrastructure.persistence.hibernate.exchange;

import game.domain.model.exchange.Exchange;
import game.domain.model.exchange.IExchangeRepository;
import game.infrastructure.persistence.hibernate.generic.admin.AdminAbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Repository("exchangeRepository")
public class ExchangeRepository extends AdminAbstractHibernateGenericRepository<Exchange, String>
        implements IExchangeRepository<Exchange, String> {
}
