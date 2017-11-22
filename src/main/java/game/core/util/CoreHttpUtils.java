package game.core.util;

import game.core.common.CharsetConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;

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
            if (null != pa) {
                pw.print(pa);
            }
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

    public static String urlConnectionByRsa(String url, String pa) {

        try {
            Key publicKey = RSAUtils.getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDmlCWDcOa9hOWq+ZTmuaKAr7yQqRRGBNb1LtYAlXMtuuXCWMSGdRiIrRrEsTDDBNRcjjm+slFt0BOCZoR4xtcO9d4+SLkg8mIJnDaLPnNsSM1GVuxMGTjdqT9jl/N7LBkHuW3JeIlZ5qk/7iX3JCUzXxGbs6aHnP2KW9RvXrdvPQIDAQAB");
            if (CoreStringUtils.isEmpty(pa)) {
                return new String(RSAUtils.decrypt(publicKey, urlConnection(url, null, "utf-8")), "utf-8");
            }
            byte[] bytes = RSAUtils.encrypt(publicKey, pa);
            return new String(RSAUtils.decrypt(publicKey, urlConnection(url, URLEncoder.encode(new String(bytes, "utf-8"), "utf-8"), "utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
