package niuniu.domain.model.seal;


import niuniu.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface ISealRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    T bySealNo(String sealNo);
}
