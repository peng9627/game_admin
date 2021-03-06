package game.domain.model.rewarddetailed;

import game.core.enums.FlowType;
import game.core.id.ConcurrencySafeEntity;
import game.domain.model.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi on 2016/3/9.
 */
public class RewardDetailed extends ConcurrencySafeEntity {

    private User user;                  //用户
    private FlowType flowType;          //资金流向类型
    private BigDecimal money;                  //金额
    private String description;         //说明（）
    private BigDecimal oldMoney;              //原有金额
    private BigDecimal newMoney;              //现有金额

    private void setUser(User user) {
        this.user = user;
    }

    private void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public User getUser() {
        return user;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(BigDecimal oldMoney) {
        this.oldMoney = oldMoney;
    }

    public BigDecimal getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(BigDecimal newMoney) {
        this.newMoney = newMoney;
    }

    public RewardDetailed() {
        super();
    }

    public RewardDetailed(User user, FlowType flowType, BigDecimal money, String description, BigDecimal oldMoney, BigDecimal newMoney) {
        this.user = user;
        this.flowType = flowType;
        this.money = money;
        this.description = description;
        this.oldMoney = oldMoney;
        this.newMoney = newMoney;
        this.setCreateDate(new Date());
    }
}
