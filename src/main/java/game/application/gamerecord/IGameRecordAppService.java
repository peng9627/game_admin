package game.application.gamerecord;

import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.application.gamerecord.representation.GameRecordInfoRepresentation;
import game.application.gamerecord.representation.GameRecordRepresentation;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public interface IGameRecordAppService {

    void create(CreateCommand command);

    List<GameRecordRepresentation> list(ListCommand command);

    GameRecordInfoRepresentation info(String id);
}
