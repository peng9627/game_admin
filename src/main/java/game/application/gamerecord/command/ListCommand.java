package game.application.gamerecord.command;

import game.core.enums.GameType;

/**
 * Created by pengyi
 * Date : 17-8-21.
 * desc:
 */
public class ListCommand {

    private GameType gameType;
    private int userId;
    private int roomNo;

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}
