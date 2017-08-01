package niuniu.infrastructure.persistence.hibernate.golddetailed;

import niuniu.domain.model.golddetailed.GoldDetailed;
import niuniu.domain.model.golddetailed.IGoldDetailedRepository;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("goldDetailedRepository")
public class GoldDetailedRepository extends AbstractHibernateGenericRepository<GoldDetailed, String>
        implements IGoldDetailedRepository<GoldDetailed, String> {
}
