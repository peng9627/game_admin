package game.application.moneydetailed.representation;

import game.core.enums.FlowType;

import java.util.Date;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
public class MoneyDetailedRepresentation {
    private String id;
    private Integer version;
    private Date createDate;

    private int userId;         //用户id
    private String nickname;    //用户昵称
    private FlowType flowType;  //资金流向类型
    private int money;          //金额
    private String description; //说明（）
    private Long oldMoney;      //原有金额
    private Long newMoney;      //现有金额

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(Long oldMoney) {
        this.oldMoney = oldMoney;
    }

    public Long getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(Long newMoney) {
        this.newMoney = newMoney;
    }
}
