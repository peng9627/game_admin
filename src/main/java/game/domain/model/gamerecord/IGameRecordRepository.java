package game.domain.model.gamerecord;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by zhangjin on 2017/6/1.
 */
public interface IGameRecordRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
