package game.domain.model.seal;


import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2016/3/30.
 */
public interface ISealRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    T bySealNo(String sealNo);
}
