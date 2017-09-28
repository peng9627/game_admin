package game.domain.model.system;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
public interface ISystemRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}