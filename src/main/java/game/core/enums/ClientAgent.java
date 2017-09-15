package game.core.enums;

/**
 * Created by pengyi on 16-9-5.
 */
public enum ClientAgent {

    ANDROID("安卓", 1),
    IOS("ios", 2),
    PC("pc", 2),
    OTHER("其它", 3);

    ClientAgent(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private int value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

}
