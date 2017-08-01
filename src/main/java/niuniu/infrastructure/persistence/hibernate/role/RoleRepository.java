package niuniu.infrastructure.persistence.hibernate.role;


import niuniu.domain.model.role.IRoleRepository;
import niuniu.domain.model.role.Role;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/3/30.
 */
@Repository("roleRepository")
public class RoleRepository extends AbstractHibernateGenericRepository<Role, String>
        implements IRoleRepository<Role, String> {
    @Override
    public Role searchByName(String name) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("name", name));
        return (Role) criteria.uniqueResult();
    }
}
