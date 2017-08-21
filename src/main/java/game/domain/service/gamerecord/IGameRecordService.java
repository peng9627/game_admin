package game.domain.service.gamerecord;

import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.domain.model.gamerecord.GameRecord;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public interface IGameRecordService {

    void create(CreateCommand command);

    List<GameRecord> list(ListCommand command);
}
