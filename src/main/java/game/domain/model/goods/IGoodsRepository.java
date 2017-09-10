package game.domain.model.goods;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi
 * Date : 17-8-30.
 * desc:
 */
public interface IGoodsRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
