package game.interfaces.user.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import game.application.user.IUserAppService;
import game.application.user.command.EditCommand;
import game.application.user.command.LoginCommand;
import game.application.user.representation.UserRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.api.SocketRequest;
import game.core.exception.ApiAuthenticationException;
import game.core.exception.ExistException;
import game.core.exception.NoFoundException;
import game.core.util.CoreHttpUtils;
import game.core.util.CoreStringUtils;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Author pengyi
 * Date 17-5-25.
 */
@Controller
@RequestMapping("/api/user")
public class ApiUserController extends BaseApiController {

    private final IUserAppService userAppService;

    @Autowired
    public ApiUserController(IUserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /**
     * 微信登陆
     *
     * @param request  Request
     * @param response Response
     */
    @RequestMapping(value = "/login_wechat")
    public void loginWeChat(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<UserRepresentation> apiResponse;
        try {
            LoginCommand command = this.authenticationAndConvert(request, LoginCommand.class);
            UserRepresentation userRepresentation = userAppService.weChatLogin(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentation);

        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/info")
    public void info(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<UserRepresentation> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            UserRepresentation userRepresentation = userAppService.info(command.getInteger("userId"));
            userRepresentation.setSpreadCount(userAppService.spreadCount(command.getInteger("userId")));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentation);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/share")
    public void share(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            userAppService.share(command.getInteger("userId"));
            apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (ExistException e) {
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_SHARED);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<List<UserRepresentation>> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            List<UserRepresentation> userRepresentations = userAppService.list(command.getString("userIds"));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentations);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/online")
    public void online(HttpServletResponse response) {
        ApiResponse<List<Integer>> apiResponse;
        try {
            List<Integer> integers = new ArrayList<>();
            String hall = CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10410/4", null);
            if (!CoreStringUtils.isEmpty(hall)) {
                ApiResponse<Integer> hallResponse = JSON.parseObject(hall, new TypeReference<ApiResponse<Integer>>() {
                });
                integers.add(hallResponse.getData());
            } else {
                integers.add(0);
            }

            int gameCount = 0;
            String mahjong = CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10411/2", null);
            if (!CoreStringUtils.isEmpty(mahjong)) {
                ApiResponse<Integer> mahjongResponse = JSON.parseObject(mahjong, new TypeReference<ApiResponse<Integer>>() {
                });
                gameCount += mahjongResponse.getData();
            }
            mahjong = CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10412/2", null);
            if (!CoreStringUtils.isEmpty(mahjong)) {
                ApiResponse<Integer> mahjongResponse = JSON.parseObject(mahjong, new TypeReference<ApiResponse<Integer>>() {
                });
                gameCount += mahjongResponse.getData();
            }
            mahjong = CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10414/2", null);
            if (!CoreStringUtils.isEmpty(mahjong)) {
                ApiResponse<Integer> mahjongResponse = JSON.parseObject(mahjong, new TypeReference<ApiResponse<Integer>>() {
                });
                gameCount += mahjongResponse.getData();
            }
            integers.add(gameCount);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, integers);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/updateInfo")
    public void updateInfo(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<List<UserRepresentation>> apiResponse;
        try {
            EditCommand command = this.authenticationAndConvert(request, EditCommand.class);
            userAppService.update(command);
            if (0 != command.getParent()) {
                SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty,
                        SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                        SerializerFeature.WriteNullBooleanAsFalse};
                int ss = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
                SocketRequest socketRequest = new SocketRequest();
                socketRequest.setUserId(command.getParent());
                CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10410/1", JSON.toJSONString(socketRequest, ss, features));
            }
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS);
        } catch (NoFoundException e) {
            apiResponse = new ApiResponse<>(ApiReturnCode.NO_FOUND);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/ranking")
    public void ranking(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<List<UserRepresentation>> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            List<UserRepresentation> userRepresentations = userAppService.ranking(command.getIntValue("rankingType"));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentations);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

}
