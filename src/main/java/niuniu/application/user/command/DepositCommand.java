package niuniu.application.user.command;

import java.math.BigDecimal;

/**
 * Author pengyi
 * Date 16-10-17.
 */
public class DepositCommand {
    private String userId;
    private BigDecimal money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
