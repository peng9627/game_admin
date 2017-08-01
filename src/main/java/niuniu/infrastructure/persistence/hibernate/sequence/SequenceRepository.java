package niuniu.infrastructure.persistence.hibernate.sequence;

import niuniu.domain.model.sequence.ISequenceRepository;
import niuniu.domain.model.sequence.Sequence;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/3/22.
 */
@Repository("sequenceRepository")
public class SequenceRepository extends AbstractHibernateGenericRepository<Sequence, String>
        implements ISequenceRepository<Sequence, String> {
}
