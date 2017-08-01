package niuniu.application.recharge.command;

import niuniu.core.enums.PayType;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/4/11.
 */
public class CreateRechargeCommand {

    private BigDecimal money;
    private String userId;
    private PayType payType;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }
}

