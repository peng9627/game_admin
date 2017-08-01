package niuniu.infrastructure.persistence.hibernate.system;

import niuniu.domain.model.system.ISystemRepository;
import niuniu.domain.model.system.System;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/4/15.
 */
@Repository("systemRepository")
public class SystemRepository extends AbstractHibernateGenericRepository<System, String>
        implements ISystemRepository<System, String> {
}
