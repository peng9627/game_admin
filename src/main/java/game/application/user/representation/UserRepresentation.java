package game.application.user.representation;

import game.application.account.representation.AccountRepresentation;
import game.core.enums.Sex;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/4/19.
 */
public class UserRepresentation extends AccountRepresentation {

    private String name;                //网名
    private BigDecimal money;           //金币
    private Sex sex;
    private String parent;
    private String deviceNo;            //设备号
    private Boolean vip;              //是否vip
    private String phoneNo;            //手机号
    private Integer ranking;        //排行

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

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
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
}
