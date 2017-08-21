package game.infrastructure.persistence.hibernate.gamerecord;

import game.domain.model.gamerecord.GameRecord;
import game.domain.model.gamerecord.IGameRecordRepository;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi
 * Date : 17-8-21.
 * desc:
 */
@Repository("gameRecordRepository")
public class GameRecordRepository extends AbstractHibernateGenericRepository<GameRecord, String>
        implements IGameRecordRepository<GameRecord, String> {
}