package game.core.enums;

/**
 * Created by pengyi on 16-7-5.
 */
public enum NoticeType {

    GOODS("商城", 1),
    CURRENCY("货币", 2);

    NoticeType(String name, int value) {
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
