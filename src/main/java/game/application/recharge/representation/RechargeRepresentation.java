package game.application.recharge.representation;


import game.core.enums.PayType;
import game.core.enums.YesOrNoStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
public class RechargeRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private int user;              //用户
    private BigDecimal money;       //金额
    private PayType payType;        //支付方式
    private YesOrNoStatus isSuccess;//是否成功

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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public YesOrNoStatus getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(YesOrNoStatus isSuccess) {
        this.isSuccess = isSuccess;
    }
}
