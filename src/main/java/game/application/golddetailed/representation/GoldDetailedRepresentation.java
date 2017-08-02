package game.application.golddetailed.representation;

import game.application.user.representation.UserRepresentation;
import game.core.enums.FlowType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
public class GoldDetailedRepresentation {
    private String id;
    private Integer version;
    private Date createDate;

    private UserRepresentation user;      //用户
    private FlowType flowType;  //资金流向类型
    private BigDecimal gold;   //金额
    private String description;     //说明（）
    private BigDecimal oldGold;    //原有金额
    private BigDecimal newGold;    //现有金额

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

    public UserRepresentation getUser() {
        return user;
    }

    public void setUser(UserRepresentation user) {
        this.user = user;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOldGold() {
        return oldGold;
    }

    public void setOldGold(BigDecimal oldGold) {
        this.oldGold = oldGold;
    }

    public BigDecimal getNewGold() {
        return newGold;
    }

    public void setNewGold(BigDecimal newGold) {
        this.newGold = newGold;
    }
}
