package game.domain.model.user;

import game.core.enums.ClientAgent;
import game.core.enums.Sex;
import game.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * 用户
 * Created by pengyi on 2016/4/15.
 */
public class User extends ConcurrencySafeEntity {

    private Integer userId;             //用户id
    private String nickname;            //网名
    private String head;                //头像
    private ClientAgent agent;          //终端
    private Long money;                 //房卡
    private Sex sex;
    private String weChatNo;            //微信号
    private String registerIp;          //注册ip
    private String lastLoginIp;         //上次登陆ip
    private String area;                //地方
    private Long gameCount;             //游戏局数
    private Date lastLoginDate;         //上次登陆时间
    private Boolean status;             //状态

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public ClientAgent getAgent() {
        return agent;
    }

    public void setAgent(ClientAgent agent) {
        this.agent = agent;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getGameCount() {
        return gameCount;
    }

    public void setGameCount(Long gameCount) {
        this.gameCount = gameCount;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User() {
    }

    public User(Integer userId, String weChatNo) {
        setCreateDate(new Date());
        this.userId = userId;
        this.weChatNo = weChatNo;
        this.money = 0L;
        this.gameCount = 0L;
        this.lastLoginDate = new Date();
        this.status = true;
    }
}
