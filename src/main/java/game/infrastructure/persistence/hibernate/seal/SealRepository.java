package game.infrastructure.persistence.hibernate.seal;


import game.domain.model.seal.ISealRepository;
import game.domain.model.seal.Seal;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/3/30.
 */
@Repository("sealRepository")
public class SealRepository extends AbstractHibernateGenericRepository<Seal, String>
        implements ISealRepository<Seal, String> {
    @Override
    public Seal bySealNo(String sealNo) {

        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("sealNo", sealNo));
        return (Seal) criteria.uniqueResult();
    }
}
