package game.core.api;

/**
 * Created by pengyi on 2016/4/15.
 */
public enum ApiReturnCode {

    // 通用错误码
    SUCCESS("处理成功", 10000),
    FAILURE("处理失败", 10001),
    ERROR_UNKNOWN("未知错误", 10002),
    NO_FOUND("数据不存在", 10003),
    ERROR_DATA("数据异常", 10004),

    //api 错误码
    AUTHENTICATION_FAILURE("鉴权失败", 20000),
    ILLEGAL_ARGUMENT("不合法参数", 20001),

    ERROR_DATA_NOT_FOUND("相关数据没有找到", 30001),
    ERROR_ACCOUNT_LOCKED("账户被禁用", 30002),
    ERROR_SHARED("已经分享过", 40001);


    ApiReturnCode(String name, int value) {
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
