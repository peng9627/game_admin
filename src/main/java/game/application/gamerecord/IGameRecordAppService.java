package game.application.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public interface IGameRecordAppService {

    void create(CreateGameRecordCommand command);
}
