package game.infrastructure.persistence.hibernate.goods;

import game.domain.model.goods.Goods;
import game.domain.model.goods.IGoodsRepository;
import game.infrastructure.persistence.hibernate.generic.admin.AdminAbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Repository("goodsRepository")
public class GoodsRepository extends AdminAbstractHibernateGenericRepository<Goods, String>
        implements IGoodsRepository<Goods, String> {
}
