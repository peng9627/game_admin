package game.interfaces.user.api;

import com.alibaba.fastjson.JSONObject;
import game.application.user.IUserAppService;
import game.application.user.command.LoginCommand;
import game.application.user.representation.UserRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.exception.ApiAuthenticationException;
import game.core.exception.ExistException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

}
