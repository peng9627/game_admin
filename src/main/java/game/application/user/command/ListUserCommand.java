package game.application.user.command;

import game.core.common.BasicPaginationCommand;
import game.core.enums.EnableStatus;

/**
 * Created by pengyi on 2016/4/19.
 */
public class ListUserCommand extends BasicPaginationCommand {

    private String userName;        //用户名
    private String nickName;        //昵称

    private String parent;          //上级
    private String parentName;      //上级

    private EnableStatus status;    //状态

    private String user;            //用户

    private String deviceNo;        //设备号

    private Boolean vip;            //是否vip

    private String order;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
