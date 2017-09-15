package game.infrastructure.persistence.hibernate.integral;

import game.domain.model.integraldetailed.IIntegralDetailedRepository;
import game.domain.model.integraldetailed.IntegralDetailed;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("integralDetailedRepository")
public class IntegralDetailedRepository extends AbstractHibernateGenericRepository<IntegralDetailed, String>
        implements IIntegralDetailedRepository<IntegralDetailed, String> {
}
