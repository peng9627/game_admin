package game.interfaces.shared.web;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by pengyi on 2016/3/30.
 */
public class JsonMessage {
    private int code;
    private String message;
    private Object data;

    public JsonMessage() {
        super();
    }

    public JsonMessage(int code, String message, JSONArray data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
