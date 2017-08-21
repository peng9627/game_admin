package game.application.user.command;

import game.core.enums.ClientAgent;
import game.core.enums.Sex;

public class LoginCommand {

    private Sex sex;
    private String weChatNo;            //微信号
    private String area;                //地方
    private String nickname;            //昵称
    private String head;                //头像
    private ClientAgent agent;          //登录终端
    private String ip;                  //ip

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
