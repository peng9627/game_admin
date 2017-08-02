package game.application.recharge.representation;

import game.core.enums.PayType;
import game.core.enums.YesOrNoStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
public class ApiRechargeRepresentation {

    private String rechargeNo;      //充值号
    private Date createDate;
    private BigDecimal money;       //金额
    private PayType payType;        //支付方式
    private YesOrNoStatus isSuccess;//是否成功

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
