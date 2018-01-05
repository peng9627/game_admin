package game.application.recharge.command;


import game.core.enums.PayType;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/4/11.
 */
public class CreateRechargeCommand {

    private BigDecimal money;
    private int userId;
    private PayType payType;
    private String ip;
    private String id;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

