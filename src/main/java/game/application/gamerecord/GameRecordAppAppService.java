package game.application.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;
import game.domain.service.gamerecord.IGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
@Service("gameRecordAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GameRecordAppAppService implements IGameRecordAppService {

    private final IGameRecordService gameRecordService;

    @Autowired
    public GameRecordAppAppService(IGameRecordService gameRecordService) {
        this.gameRecordService = gameRecordService;
    }


    @Override
    public void create(CreateGameRecordCommand command) {
        gameRecordService.create(command);
    }
}
