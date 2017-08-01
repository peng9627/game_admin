package niuniu.application.recharge.command;

import java.math.BigDecimal;

/**
 * Created by apple on 2016/9/16.
 */
public class CreateRechargeGiveCommand {

    private BigDecimal money;
    private BigDecimal giveMoney;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }
}
