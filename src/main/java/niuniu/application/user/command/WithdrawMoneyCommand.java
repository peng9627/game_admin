package niuniu.application.user.command;

import java.math.BigDecimal;

/**
 * Author pengyi
 * Date 16-10-17.
 */
public class WithdrawMoneyCommand {

    private String userId;
    private String payPassword;
    private BigDecimal money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
