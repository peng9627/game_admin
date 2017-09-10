package game.domain.model.exchange;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-8-30.
 * desc:
 */
public interface IExchangeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
