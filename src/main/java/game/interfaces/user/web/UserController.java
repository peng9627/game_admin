package game.interfaces.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import game.application.user.IUserAppService;
import game.application.user.representation.UserRepresentation;
import game.core.api.SocketRequest;
import game.core.util.CoreHttpUtils;
import game.core.util.CoreStringUtils;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Author pengyi
 * Date 17-5-25.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseApiController {

    private final IUserAppService userAppService;

    @Autowired
    public UserController(IUserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /**
     * 微信登陆
     *
     * @param request Request
     */
    @RequestMapping(value = "/login_wechat")
    public ModelAndView loginWeChat(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
        UserRepresentation userRepresentation = null;
        try {
            Map<String, String[]> map = request.getParameterMap();
            String code = "";
            String state = "";
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                if (null != entry.getValue()) {
                    for (String s : entry.getValue()) {
                        if (entry.getKey().equals("code")) {
                            code = s;
                        } else if (entry.getKey().equals("state")) {
                            state = s;
                        }
                    }
                }
            }

            JSONObject userinfoJson = new JSONObject();
            if (!CoreStringUtils.isEmpty(code)) {
                String access_token = CoreHttpUtils.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb56d7a60de4171bc&secret=786a9ef9b577203011b3bcc490b0d9ec&code=" + code + "&grant_type=authorization_code", "utf-8");
                System.out.println("access_token--------" + access_token);
                JSONObject jsonObject = JSON.parseObject(access_token);
                if (jsonObject.containsKey("access_token")) {
                    String check = CoreHttpUtils.get("https://api.weixin.qq.com/sns/auth?access_token=" + jsonObject.getString("access_token") + "&openid=" + jsonObject.getString("openid"), "utf-8");
                    System.out.println("check--------" + check);
                    JSONObject checkJson = JSON.parseObject(check);
                    if (checkJson.containsKey("errcode") && 0 == checkJson.getIntValue("errcode")) {
                        String userinfo = CoreHttpUtils.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonObject.getString("access_token") + "&openid=" + jsonObject.getString("openid") + "&lang=zh_CN", "utf-8");
                        userinfoJson = JSON.parseObject(userinfo);
                        System.out.println("userinfo--------" + userinfo);
                    } else {
                        String refresh = CoreHttpUtils.get("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wxb56d7a60de4171bc&grant_type=refresh_token&refresh_token=" + jsonObject.getString("refresh_token"), "utf-8");
                        jsonObject = JSON.parseObject(refresh);
                        String userinfo = CoreHttpUtils.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonObject.getString("access_token") + "&openid=" + jsonObject.getString("openid") + "&lang=zh_CN", "utf-8");
                        userinfoJson = JSON.parseObject(userinfo);
                        System.out.println("userinfo--------" + userinfo);
                    }
                }
            }
            if (userinfoJson.containsKey("unionid")) {
                userinfoJson.put("parent", Integer.parseInt(state));
                userRepresentation = userAppService.loginAndBindParent(userinfoJson);
                httpSession.setAttribute("userId", userRepresentation.getUserId());
                SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty,
                        SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                        SerializerFeature.WriteNullBooleanAsFalse};
                int ss = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
                SocketRequest socketRequest = new SocketRequest();
                socketRequest.setUserId(Integer.parseInt(state));
                CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10110/1", JSON.toJSONString(socketRequest, ss, features));
            }
//            return new ModelAndView("redirect:/user/person");
            response.sendRedirect("http://sjhxz.chuangmikeji.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("");
    }

    @RequestMapping(value = "/person")
    public ModelAndView person(HttpSession session) {
        UserRepresentation userRepresentation = null;
        try {
            if (null != session) {
                Integer userId = (Integer) session.getAttribute("userId");
                if (null != userId) {
                    userRepresentation = userAppService.info(userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/person", "userinfo", userRepresentation);
    }
}
