package game.infrastructure.persistence.hibernate.gamerecord;

import game.domain.model.gamerecord.GameRecord;
import game.domain.model.gamerecord.IGameRecordRepository;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangjin on 2017/6/1.
 */
@Repository("gameRecordRepository")
public class GameRecordRepository extends AbstractHibernateGenericRepository<GameRecord, String>
        implements IGameRecordRepository<GameRecord, String> {

}
