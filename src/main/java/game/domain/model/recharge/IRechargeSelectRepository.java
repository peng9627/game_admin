package game.domain.model.recharge;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 16-7-9.
 */
public interface IRechargeSelectRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
