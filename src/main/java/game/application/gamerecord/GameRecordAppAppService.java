package game.application.gamerecord;

import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.application.gamerecord.representation.GameRecordInfoRepresentation;
import game.application.gamerecord.representation.GameRecordRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.gamerecord.IGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
@Service("gameRecordAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GameRecordAppAppService implements IGameRecordAppService {

    private final IGameRecordService gameRecordService;
    private final IMappingService mappingService;

    @Autowired
    public GameRecordAppAppService(IGameRecordService gameRecordService, IMappingService mappingService) {
        this.gameRecordService = gameRecordService;
        this.mappingService = mappingService;
    }


    @Override
    public void create(CreateCommand command) {
        gameRecordService.create(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameRecordRepresentation> list(ListCommand command) {
        return mappingService.mapAsList(gameRecordService.list(command), GameRecordRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public GameRecordInfoRepresentation info(String id) {
        return mappingService.map(gameRecordService.info(id), GameRecordInfoRepresentation.class, false);
    }
}
