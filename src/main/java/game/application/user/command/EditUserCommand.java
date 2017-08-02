package game.application.user.command;

import game.application.shared.command.SharedCommand;

/**
 * Created by pengyi on 2016/4/19.
 */
public class EditUserCommand extends SharedCommand {

    private String name;                //网名
    private String head;                //头像
    private String phoneNo;             //手机号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
