package game.application.user.command;

import game.core.common.BasicPaginationCommand;
import game.core.enums.GameType;

/**
 * Created by pengyi
 * Date : 2016/8/9.
 */
public class RankingListCommand extends BasicPaginationCommand {

    private GameType gameType;

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
