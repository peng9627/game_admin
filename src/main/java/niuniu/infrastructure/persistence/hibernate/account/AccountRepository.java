package niuniu.infrastructure.persistence.hibernate.account;


import niuniu.domain.model.account.Account;
import niuniu.domain.model.account.IAccountRepository;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/3/30.
 */
@Repository("accountRepository")
public class AccountRepository extends AbstractHibernateGenericRepository<Account, String>
        implements IAccountRepository<Account, String> {
    @Override
    public Account searchByAccountName(String userName) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (Account) criteria.uniqueResult();
    }
}
