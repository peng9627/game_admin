package niuniu.domain.model.sequence;


import niuniu.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2016/3/22.
 */
public interface ISequenceRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
