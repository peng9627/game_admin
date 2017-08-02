package game.application.gamerecord.command;

import game.core.common.BasicPaginationCommand;
import game.core.enums.GameType;


/**
 * Created by zhangjin on 2017/6/1.
 */
public class ListGameRecordCommand extends BasicPaginationCommand {

    private String user;
    private GameType gameType;
    private String roomOwner;//房主
    private Integer roomNo;

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner;
    }
}
