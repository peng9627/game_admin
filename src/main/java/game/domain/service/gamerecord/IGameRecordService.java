package game.domain.service.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public interface IGameRecordService {

    void create(CreateGameRecordCommand command);
}
