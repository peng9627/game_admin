package game.domain.model.moneydetailed;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 2016/3/9.
 */
public interface IMoneyDetailedRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
