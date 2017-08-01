package niuniu.domain.model.golddetailed;

import niuniu.core.enums.FlowType;
import niuniu.core.id.ConcurrencySafeEntity;
import niuniu.domain.model.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi on 2016/3/9.
 */
public class GoldDetailed extends ConcurrencySafeEntity {

    private User user;      //用户
    private FlowType flowType;  //资金流向类型
    private BigDecimal gold;   //金额
    private String description;     //说明（）
    private BigDecimal oldGold;    //原有金额
    private BigDecimal newGold;    //现有金额

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public GoldDetailed() {
        super();
    }

    public GoldDetailed(User user, FlowType flowType, BigDecimal gold, String description, BigDecimal oldGold, BigDecimal newGold) {
        this.user = user;
        this.flowType = flowType;
        this.gold = gold;
        this.description = description;
        this.oldGold = oldGold;
        this.newGold = newGold;
        this.setCreateDate(new Date());
    }
}
