package niuniu.domain.model.user;

import niuniu.core.enums.EnableStatus;
import niuniu.core.enums.Sex;
import niuniu.domain.model.account.Account;
import niuniu.domain.model.role.Role;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * Created by pengyi on 2016/4/15.
 */
public class User extends Account {

    private User parent;                //父级用户
    private String name;                //网名
    private BigDecimal money;           //金币
    private Sex sex;
    private String deviceNo;            //设备号
    private Boolean vip;                //是否vip
    private String phoneNo;             //手机号
    private BigDecimal spreadCanGet;    //推荐可领取
    private Integer ranking;            //排行
    private BigDecimal spreadGetted;     //推荐领取过
    private String inviteCode;           //邀请码
    private String weChatNo;        //微信号
    private BigDecimal gold;        //金币
    private Integer days;           //连续登陆天数
    private Integer reward;         //连续登陆奖励剩余领取次数
    private Integer benefit;        //补助剩余领取次数

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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void changeVip(boolean vip) {
        this.vip = vip;
    }

    private void setParent(User parent) {
        this.parent = parent;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setMoney(BigDecimal money) {
        this.money = money;
    }

    private void setSex(Sex sex) {
        this.sex = sex;
    }

    private void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    private void setVip(Boolean vip) {
        this.vip = vip;
    }

    private void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    private void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    private void setSpreadCanGet(BigDecimal spreadCanGet) {
        this.spreadCanGet = spreadCanGet;
    }

    private void setSpreadGetted(BigDecimal spreadGeted) {
        this.spreadGetted = spreadGeted;
    }

    public User getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Sex getSex() {
        return sex;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public Boolean getVip() {
        return vip;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Integer getRanking() {
        return ranking;
    }

    public BigDecimal getSpreadCanGet() {
        return spreadCanGet;
    }

    public BigDecimal getSpreadGetted() {
        return spreadGetted;
    }

    public User() {
        super();
    }

    public User(String token, String head, Sex sex, String userName, String password, String salt, String lastLoginIP, Date lastLoginDate, String lastLoginPlatform,
                List<Role> roles, EnableStatus status, String name, String deviceNo) {
        super(token, head, userName, password, salt, lastLoginIP, lastLoginDate, lastLoginPlatform, roles, status);
        this.sex = sex;
        this.name = name;
        this.money = BigDecimal.ZERO;
        this.gold = BigDecimal.valueOf(10000);
        this.deviceNo = deviceNo;
        this.vip = false;
        this.setCreateDate(new Date());
        this.ranking = 10000;
        this.spreadCanGet = BigDecimal.ZERO;
        this.spreadGetted = BigDecimal.ZERO;
    }

    public void changeParent(User parent) {
        this.parent = parent;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeSex(Sex sex) {
        this.sex = sex;
    }

    public void changeMoney(BigDecimal money) {
        this.money = money;
    }

    public void changePhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void changeRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public void changeSpreadCanGet(BigDecimal spreadCanGet) {
        this.spreadCanGet = spreadCanGet;
    }

    public void changeSpreadGetted(BigDecimal spreadGeted) {
        this.spreadGetted = spreadGeted;
    }
}
