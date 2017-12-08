package game.infrastructure.persistence.hibernate.rewarddetailed;

import game.domain.model.rewarddetailed.IRewardDetailedRepository;
import game.domain.model.rewarddetailed.RewardDetailed;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 16-7-9.
 */
@Repository("rewardDetailedRepository")
public class RewardDetailedRepository extends AbstractHibernateGenericRepository<RewardDetailed, String>
        implements IRewardDetailedRepository<RewardDetailed, String> {
}
