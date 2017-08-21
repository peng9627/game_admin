package game.domain.service.gamerecord;

import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.domain.model.gamerecord.GameRecord;
import game.domain.model.gamerecord.IGameRecordRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
@Service("gameRecordService")
public class GameRecordService implements IGameRecordService {

    private final IGameRecordRepository<GameRecord, String> gameRecordRepository;

    @Autowired
    public GameRecordService(IGameRecordRepository<GameRecord, String> gameRecordRepository) {
        this.gameRecordRepository = gameRecordRepository;
    }

    @Override
    public void create(CreateCommand command) {

        GameRecord gameRecord = new GameRecord(command.getGameType(), command.getRoomOwner(), command.getPeople()
                , command.getGameTotal(), command.getGameCount(), command.getPeopleCount(), command.getRoomNo(),
                gameRecordRepository.getSession().getLobHelper().createBlob(command.getGameData()));
        gameRecordRepository.save(gameRecord);
    }

    @Override
    public List<GameRecord> list(ListCommand command) {

        List<Criterion> criterionList = new ArrayList<>();

        if (null != command.getGameType()) {
            criterionList.add(Restrictions.eq("gameType", command.getGameType()));
        }
        if (0 != command.getUserId()) {
            criterionList.add(Restrictions.like("people", String.valueOf(command.getUserId()), MatchMode.ANYWHERE));
        }
        if (0 != command.getRoomNo()) {
            criterionList.add(Restrictions.eq("roomNo", command.getRoomNo()));
        }

        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return gameRecordRepository.list(criterionList, orderList);
    }

}
