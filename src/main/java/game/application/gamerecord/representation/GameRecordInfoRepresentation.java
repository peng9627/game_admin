package game.application.gamerecord.representation;

import game.core.enums.GameType;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public class GameRecordInfoRepresentation {

    private GameType gameType;
    private byte[] data;

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
