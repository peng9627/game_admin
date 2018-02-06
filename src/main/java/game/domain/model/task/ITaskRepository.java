package game.domain.model.task;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
public interface ITaskRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}