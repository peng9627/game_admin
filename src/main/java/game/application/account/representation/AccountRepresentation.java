package game.application.account.representation;


import game.core.enums.EnableStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
public class AccountRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String userName;        //用户名
    private String password;        //密码
    private String salt;            //密码加密盐
    private String lastLoginIP;     //最后登录ip
    private Date lastLoginDate;     //最后登录时间
    private String lastLoginPlatform;//最后登录平台
    private List<String> roles;               //用户角色
    private List<String> permissions;               //权限
    private EnableStatus status;     //状态
    private String head;        //头像

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginPlatform() {
        return lastLoginPlatform;
    }

    public void setLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}