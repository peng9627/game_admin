package game.infrastructure.persistence.hibernate.golddetailed;

import game.domain.model.golddetailed.GoldDetailed;
import game.domain.model.golddetailed.IGoldDetailedRepository;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("goldDetailedRepository")
public class GoldDetailedRepository extends AbstractHibernateGenericRepository<GoldDetailed, String>
        implements IGoldDetailedRepository<GoldDetailed, String> {
}
