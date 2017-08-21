package game.infrastructure.persistence.hibernate.user;

import game.domain.model.user.IUserRepository;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pengyi on 2016/4/15.
 */
@Repository("userRepository")
public class UserRepository extends AbstractHibernateGenericRepository<User, String>
        implements IUserRepository<User, String> {

    @Override
    public BigDecimal totalMoney(List<Criterion> criterionList) {
        Criteria criteriaCount = getSession().createCriteria(getPersistentClass());
        criteriaCount.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (null != criterionList) {
            criterionList.forEach(criteriaCount::add);
        }

        criteriaCount.setProjection(Projections.sum("money"));
        return (BigDecimal) criteriaCount.uniqueResult();
    }

    @Override
    public User searchByUserId(int userId) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userId", userId));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User searchByWechat(String wechat) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("weChatNo", wechat));
        return (User) criteria.uniqueResult();
    }

}
