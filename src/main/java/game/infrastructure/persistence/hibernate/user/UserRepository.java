package game.infrastructure.persistence.hibernate.user;

import game.domain.model.user.IUserRepository;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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
    public User searchByName(String userName) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User searchByName(String userName, LockMode lockMode) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        criteria.setLockMode(lockMode);
        return (User) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchByDeviceNo(String deviceNo) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("deviceNo", deviceNo));

//        criteria.add(Restrictions.like("userName", "游客", MatchMode.ANYWHERE));

        criteria.addOrder(Order.desc("createDate"));
        return criteria.list();
    }

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
    public User searchByToken(String token) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("token", token));
        return (User) criteria.uniqueResult();
    }

}
