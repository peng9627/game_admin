package game.application.golddetailed.command;

import game.core.enums.FlowType;

import java.math.BigDecimal;

/**
 * Created by pengyi on 16-7-11.
 */
public class CreateGoldDetailedCommand {

    private String userName;
    private BigDecimal gold;
    private FlowType flowType;  //资金流向类型
    private String description;     //说明

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
