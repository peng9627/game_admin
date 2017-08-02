package game.application.gamerecord.command;


import game.core.enums.GameType;

/**
 * Created by zhangjin on 2017/6/2.
 */
public class CreateGameRecordCommand {

    private String userNames;
    private String gameInfo;

    private Integer baseScore;
    private GameType gameType;
    private String roomOwner;//房主
    private Integer totalRound;//总局数

    private Integer roomNo;//桌号
    private Integer rule;//规则
    private Boolean doubleBull;//对子牛
    private Boolean spottedBull;//五花牛
    private Boolean bombBull;//炸弹牛
    private Boolean smallBull;//五小牛
    private Boolean playerPush;//闲家推注
    private Boolean startedInto;//游戏开始后加入

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
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

    public Integer getTotalRound() {
        return totalRound;
    }

    public void setTotalRound(Integer totalRound) {
        this.totalRound = totalRound;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }

    public Boolean getDoubleBull() {
        return doubleBull;
    }

    public void setDoubleBull(Boolean doubleBull) {
        this.doubleBull = doubleBull;
    }

    public Boolean getSpottedBull() {
        return spottedBull;
    }

    public void setSpottedBull(Boolean spottedBull) {
        this.spottedBull = spottedBull;
    }

    public Boolean getBombBull() {
        return bombBull;
    }

    public void setBombBull(Boolean bombBull) {
        this.bombBull = bombBull;
    }

    public Boolean getSmallBull() {
        return smallBull;
    }

    public void setSmallBull(Boolean smallBull) {
        this.smallBull = smallBull;
    }

    public Boolean getPlayerPush() {
        return playerPush;
    }

    public void setPlayerPush(Boolean playerPush) {
        this.playerPush = playerPush;
    }

    public Boolean getStartedInto() {
        return startedInto;
    }

    public void setStartedInto(Boolean startedInto) {
        this.startedInto = startedInto;
    }
}
