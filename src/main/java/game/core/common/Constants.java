package game.core.common;

/**
 * Created by pengyi on 2016/4/10 0010.
 */
public class Constants {

    public static final String SESSION_USER = "sessionUser";
    public static final String COOKIE_USER = "cookieUser";
    public static final String PASSWORD_ENCRYP_KEY = "HTML5";
    public static final String WEB_SOCKET_USER = "webSocketUser";

    public static String WECHAT_APPID = "wx8149cbf48ff3ff38";                       //appid
    public static String WECHAT_KEY = "uveErVZTZKOwWc2OtWBNqY8lbRyHt6uM";           //微信支付key
    //    public static String WECHAT_APPSECRET = "85fa12372fa4bb63a80b0b0bfbfeee2d";                       //appsecret
//    public static String WECHAT_REDIRECT_SCOPE = "snsapi_base";
    public static String WECHAT_NOTIFY_URL = "http://baiwanzhifu.chuangmikeji.com/api/recharge/wechat/notify";           //微信异步通知地址
    public static String WECHAT_UNIFIED_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder"; //微信请求下单统一地址
    public static String WECHAT_MCH_ID = "1442996802";                               //商户id
}
