package niuniu.core.util;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.auth.command.LoginCommand;
import niuniu.core.common.CharsetConstant;
import niuniu.core.common.Constants;
import niuniu.core.exception.NoLoginException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pengyi on 2016/4/5 0005.
 */
public class CoreHttpUtils {


    private static String[] HTTP_PROXY_HEADER_NAME = new String[]{
            "CLIENTIP",
            "X-FORWARDED-FOR"
    };

    private enum LoginPlatform {
        LINUX("PC", 0),
        WINDOW("PC", 1),
        IPHONE("iPhone", 2),
        IPAD("iPad", 3),
        MAC("Mac", 4),
        ANDROID("Android", 5);

        private String name;
        private int value;

        LoginPlatform(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    public static String getClientIP(HttpServletRequest request) {
        for (String headerName : HTTP_PROXY_HEADER_NAME) {
            String clientIP = request.getHeader(headerName);
            if (StringUtils.isNotBlank(clientIP)) {
                return clientIP;
            }
        }
        return request.getRemoteAddr();
    }

    public static String getLoginPlatform(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if (userAgent.contains(LoginPlatform.WINDOW.name())) {
            return LoginPlatform.WINDOW.getName();
        } else if (userAgent.contains(LoginPlatform.IPHONE.name())) {
            return LoginPlatform.IPHONE.getName();
        } else if (userAgent.contains(LoginPlatform.IPAD.name())) {
            return LoginPlatform.IPAD.getName();
        } else if (userAgent.contains(LoginPlatform.MAC.name())) {
            return LoginPlatform.MAC.getName();
        } else if (userAgent.contains(LoginPlatform.ANDROID.name())) {
            return LoginPlatform.ANDROID.getName();
        } else if (userAgent.contains(LoginPlatform.LINUX.name())) {
            return LoginPlatform.LINUX.getName();
        }
        return null;
    }

    /**
     * 保存用户cookie
     *
     * @param command
     * @param request
     * @param response
     */
    public static void writeCookie(LoginCommand command, HttpServletRequest request, HttpServletResponse response) {
        if (command.isRememberMe()) {
            String encryptStr = command.getUserName() + "," + command.getPassword();
            Cookie cookie = new Cookie(Constants.COOKIE_USER, CoreRc4Utils.encry_RC4_string(encryptStr, Constants.PASSWORD_ENCRYP_KEY));
            cookie.setMaxAge(604800);
            response.addCookie(cookie);
        } else {
            clearCookie(request, response);
        }
    }

    /**
     * 移除用户保存的cookie
     *
     * @param request
     * @param response
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constants.COOKIE_USER)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static AccountRepresentation getSessionAccount(Session session) {
        AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == account) {
            throw new NoLoginException("没有登录");
        }
        return account;
    }

    public static String urlConnection(String url, String pa) {
        return urlConnection(url, pa, CharsetConstant.UTF8_STRING);
    }

    public static String urlConnection(String url, String pa, String charset) {

        String response = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("POST");


            // Send data
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
            // pa为请求的参数
            pw.print(pa);
            pw.flush();
            pw.close();

            // Get the response!
            int httpResponseCode = conn.getResponseCode();
            if (httpResponseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP response code: " + httpResponseCode +
                        "\nurl:" + url);
            }

            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
            StringBuilder builder = new StringBuilder();
            String readLine;
            while (null != (readLine = br.readLine())) {
                builder.append(readLine);
            }
            inputStream.close();
            response = builder.toString();

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
