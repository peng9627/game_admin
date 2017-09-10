package game.domain.model.arena;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
public interface IArenaRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
