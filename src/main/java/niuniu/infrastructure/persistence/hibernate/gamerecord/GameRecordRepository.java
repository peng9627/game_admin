package niuniu.infrastructure.persistence.hibernate.gamerecord;

import niuniu.domain.model.gamerecord.GameRecord;
import niuniu.domain.model.gamerecord.IGameRecordRepository;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangjin on 2017/6/1.
 */
@Repository("gameRecordRepository")
public class GameRecordRepository extends AbstractHibernateGenericRepository<GameRecord, String>
        implements IGameRecordRepository<GameRecord, String> {

}
