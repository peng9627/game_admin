package niuniu.domain.model.notice;

import niuniu.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 2016/3/9.
 */
public interface INoticeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
