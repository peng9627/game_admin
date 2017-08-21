package game.domain.service.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;
import game.domain.model.gamerecord.GameRecord;
import game.domain.model.gamerecord.IGameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void create(CreateGameRecordCommand command) {

        GameRecord gameRecord = new GameRecord(command.getGameType(), command.getRoomOwner(), command.getPeople()
                , command.getGameTotal(), command.getGameCount(), command.getPeopleCount(), command.getRoomNo(),
                gameRecordRepository.getSession().getLobHelper().createBlob(command.getGameData()));
        gameRecordRepository.save(gameRecord);
    }

}
