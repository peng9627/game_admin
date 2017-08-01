package niuniu.application.user.representation;

import niuniu.application.account.representation.ApiAccountRepresentation;
import niuniu.core.enums.Sex;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/4/19.
 */
public class ApiUserRepresentation extends ApiAccountRepresentation {

    private String name;                //网名
    private BigDecimal money;           //金币
    private Sex sex;
    private String parent;
    private Boolean vip;              //是否vip
    private String phoneNo;            //手机号
    private Integer ranking;        //排行
    private String inviteCode;           //邀请码
    private BigDecimal gold;        //金币
    private Integer days;           //连续登陆天数
    private Integer reward;         //连续登陆奖励剩余领取次数
    private Integer benefit;        //补助剩余领取次数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getBenefit() {
        return benefit;
    }

    public void setBenefit(Integer benefit) {
        this.benefit = benefit;
    }
}
