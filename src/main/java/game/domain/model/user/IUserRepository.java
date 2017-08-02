package game.domain.model.user;

import game.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pengyi on 2016/4/15.
 */
public interface IUserRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    User searchByName(String userName);

    User searchByName(String userName, LockMode lockMode);

    List<User> searchByDeviceNo(String deviceNo);

    BigDecimal totalMoney(List<Criterion> criterionList);

    User searchByToken(String token);
}
