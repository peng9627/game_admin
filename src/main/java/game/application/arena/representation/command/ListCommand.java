package game.application.arena.representation.command;

import game.core.enums.GameType;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
public class ListCommand {
    private GameType gameType;              //游戏类型

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
