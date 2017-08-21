package game.domain.model.gamerecord;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public interface IGameRecordRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
