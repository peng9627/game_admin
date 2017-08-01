package niuniu.domain.model.gamerecord;

import niuniu.core.enums.GameType;
import niuniu.core.id.ConcurrencySafeEntity;

/**
 * Author pengyi
 * Date 17-6-1.
 */
public class GameRecord extends ConcurrencySafeEntity {

    private String userNames;
    private int baseScore;
    private GameType gameType;
    private String roomOwner;//房主
    private int totalRound;//总局数

    private int roomNo;//桌号
    private int rule;//规则
    private boolean doubleBull;//对子牛
    private boolean spottedBull;//五花牛
    private boolean bombBull;//炸弹牛
    private boolean smallBull;//五小牛
    private boolean playerPush;//闲家推注
    private boolean startedInto;//游戏开始后加入
    private String gameInfo;


    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner;
    }

    public int getTotalRound() {
        return totalRound;
    }

    public void setTotalRound(int totalRound) {
        this.totalRound = totalRound;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public boolean isDoubleBull() {
        return doubleBull;
    }

    public void setDoubleBull(boolean doubleBull) {
        this.doubleBull = doubleBull;
    }

    public boolean isSpottedBull() {
        return spottedBull;
    }

    public void setSpottedBull(boolean spottedBull) {
        this.spottedBull = spottedBull;
    }

    public boolean isBombBull() {
        return bombBull;
    }

    public void setBombBull(boolean bombBull) {
        this.bombBull = bombBull;
    }

    public boolean isSmallBull() {
        return smallBull;
    }

    public void setSmallBull(boolean smallBull) {
        this.smallBull = smallBull;
    }

    public boolean isPlayerPush() {
        return playerPush;
    }

    public void setPlayerPush(boolean playerPush) {
        this.playerPush = playerPush;
    }

    public boolean isStartedInto() {
        return startedInto;
    }

    public void setStartedInto(boolean startedInto) {
        this.startedInto = startedInto;
    }

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }
}
