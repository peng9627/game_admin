package game.infrastructure.persistence.hibernate.sequence;

import game.domain.model.sequence.ISequenceRepository;
import game.domain.model.sequence.Sequence;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/3/22.
 */
@Repository("sequenceRepository")
public class SequenceRepository extends AbstractHibernateGenericRepository<Sequence, String>
        implements ISequenceRepository<Sequence, String> {
}
