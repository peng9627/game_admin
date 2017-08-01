package niuniu.domain.model.recharge;

import niuniu.core.enums.PayType;
import niuniu.core.enums.YesOrNoStatus;
import niuniu.core.id.ConcurrencySafeEntity;
import niuniu.domain.model.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值
 * <p>
 * Created by pengyi on 16-7-9.
 */
public class Recharge extends ConcurrencySafeEntity {

    private String rechargeNo;      //充值号
    private User user;              //用户
    private BigDecimal money;       //金额
    private YesOrNoStatus isSuccess;//是否成功
    private PayType payType;        //支付类型
    private String payNo;
    private Date payTime;

    public void changePayNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public void changeIsSuccess(YesOrNoStatus isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void changePayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public YesOrNoStatus getIsSuccess() {
        return isSuccess;
    }

    public PayType getPayType() {
        return payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    private void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setMoney(BigDecimal money) {
        this.money = money;
    }

    private void setIsSuccess(YesOrNoStatus isSuccess) {
        this.isSuccess = isSuccess;
    }

    private void setPayType(PayType payType) {
        this.payType = payType;
    }

    private void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    private void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Recharge() {
        super();
    }

    public Recharge(String rechargeNo, User user, BigDecimal money, YesOrNoStatus isSuccess, PayType payType) {
        this.rechargeNo = rechargeNo;
        this.user = user;
        this.money = money;
        this.isSuccess = isSuccess;
        this.payType = payType;
        this.setCreateDate(new Date());
    }
}
