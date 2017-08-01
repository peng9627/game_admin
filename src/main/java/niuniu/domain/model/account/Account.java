package niuniu.domain.model.account;


import niuniu.core.enums.EnableStatus;
import niuniu.core.id.ConcurrencySafeEntity;
import niuniu.domain.model.role.Role;

import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public class Account extends ConcurrencySafeEntity {

    private String userName;        //用户名
    private String password;        //密码
    private String salt;            //密码加密盐
    private String lastLoginIP;     //最后登录ip
    private Date lastLoginDate;     //最后登录时间
    private String lastLoginPlatform;//最后登录平台
    private List<Role> roles;               //用户角色
    private EnableStatus status;     //状态
    private String head;        //头像
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeSalt(String salt) {
        this.salt = salt;
    }

    public void changeLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public void changeLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void changeLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    public void changeRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    public void changeHead(String head) {
        this.head = head;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setSalt(String salt) {
        this.salt = salt;
    }

    private void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    private void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    private void setLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    private void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    private void setHead(String head) {
        this.head = head;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public String getLastLoginPlatform() {
        return lastLoginPlatform;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public String getHead() {
        return head;
    }

    public Account() {
        super();
    }

    public Account(String token, String head, String userName, String password, String salt, String lastLoginIP, Date lastLoginDate, String lastLoginPlatform,
                   List<Role> roles, EnableStatus status) {
        this.token = token;
        this.head = head;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.lastLoginIP = lastLoginIP;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginPlatform = lastLoginPlatform;
        this.roles = roles;
        this.status = status;
        this.head = "";
        this.setCreateDate(new Date());
    }
}
