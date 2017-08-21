package game.application.moneydetailed.command;

import game.core.enums.FlowType;

/**
 * Created by pengyi on 16-7-11.
 */
public class CreateMoneyDetailedCommand {

    private int userId;
    private int money;
    private FlowType flowType;  //资金流向类型
    private String description;     //说明

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
