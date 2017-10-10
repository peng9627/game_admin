package game.domain.model.gamerecord;

import game.core.id.ConcurrencySafeEntity;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by pengyi
 * Date : 17-8-19.
 * desc:
 */
public class GameRecord extends ConcurrencySafeEntity {

    private Integer gameType;//1.兴宁麻将 2.瑞金麻将 3.跑得快 4.三公 5.松江河
    private Integer roomOwner;
    private String people;
    private Integer gameTotal;
    private Integer gameCount;
    private Integer peopleCount;
    private Integer roomNo;
    private Blob gameData;
    private Blob scoreData;
    private String gameRule;

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public Integer getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(Integer roomOwner) {
        this.roomOwner = roomOwner;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Integer getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(Integer gameTotal) {
        this.gameTotal = gameTotal;
    }

    public Integer getGameCount() {
        return gameCount;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Blob getGameData() {
        return gameData;
    }

    public void setGameData(Blob gameData) {
        this.gameData = gameData;
    }

    public Blob getScoreData() {
        return scoreData;
    }

    public void setScoreData(Blob scoreData) {
        this.scoreData = scoreData;
    }

    public String getGameRule() {
        return gameRule;
    }

    public void setGameRule(String gameRule) {
        this.gameRule = gameRule;
    }

    public GameRecord() {
    }

    public GameRecord(Integer gameType, Integer roomOwner, String people, Integer gameTotal, Integer gameCount,
                      Integer peopleCount, Integer roomNo, Blob gameData, Blob scoreData, String gameRule) {
        setCreateDate(new Date());
        this.gameType = gameType;
        this.roomOwner = roomOwner;
        this.people = people;
        this.gameTotal = gameTotal;
        this.gameCount = gameCount;
        this.peopleCount = peopleCount;
        this.roomNo = roomNo;
        this.gameData = gameData;
        this.scoreData = scoreData;
        this.gameRule = gameRule;
    }
}
