package niuniu.domain.model.gamerecord;

import niuniu.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by zhangjin on 2017/6/1.
 */
public interface IGameRecordRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
