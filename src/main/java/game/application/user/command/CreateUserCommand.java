package game.application.user.command;

import game.application.account.command.CreateAccountCommand;

/**
 * Created by pengyi on 2016/4/19.
 */
public class CreateUserCommand extends CreateAccountCommand {

    private String parent;
    private String name;                //网名

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
