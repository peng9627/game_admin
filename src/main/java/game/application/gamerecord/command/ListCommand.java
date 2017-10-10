package game.application.gamerecord.command;

/**
 * Created by pengyi
 * Date : 17-8-21.
 * desc:
 */
public class ListCommand {

    private Integer gameType;
    private int userId;
    private int roomNo;

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
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
