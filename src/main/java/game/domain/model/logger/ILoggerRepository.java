package game.domain.model.logger;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public interface ILoggerRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
