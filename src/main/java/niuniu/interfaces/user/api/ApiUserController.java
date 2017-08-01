package niuniu.interfaces.user.api;

import com.alibaba.fastjson.JSONObject;
import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.user.IUserAppService;
import niuniu.application.user.command.InviteCodeCommand;
import niuniu.application.user.representation.ApiUserRepresentation;
import niuniu.core.api.ApiResponse;
import niuniu.core.api.ApiReturnCode;
import niuniu.core.enums.LoggerType;
import niuniu.core.exception.*;
import niuniu.core.util.CoreHttpUtils;
import niuniu.interfaces.shared.api.BaseApiController;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Author pengyi
 * Date 17-5-25.
 */
@Controller
@RequestMapping("/api/user")
public class ApiUserController extends BaseApiController {

    private final IUserAppService userAppService;

    private final ILoggerAppService loggerAppService;

    private final HttpSession httpSession;

    @Autowired
    public ApiUserController(IUserAppService userAppService, ILoggerAppService loggerAppService, HttpSession httpSession) {
        this.userAppService = userAppService;
        this.loggerAppService = loggerAppService;
        this.httpSession = httpSession;
    }

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<ApiUserRepresentation> apiResponse;
        try {
            LoginCommand command = this.authenticationAndConvert(request, LoginCommand.class);

            ApiUserRepresentation userRepresentation = userAppService.login(command);

            AccountRepresentation sessionUser = (AccountRepresentation) httpSession.getAttribute("sessionUser");
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.APP_LOGGER,
                    userRepresentation.getUserName() + "登陆成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentation);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (AccountException | ExcessiveAttemptsException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_ACCOUNT_EXCEPTION);
        } catch (LockedAccountException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_ACCOUNT_LOCKED);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    /**
     * 微信登陆
     *
     * @param request  Request
     * @param response Response
     */
    @RequestMapping(value = "/login_wechat")
    public void loginWeChat(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<ApiUserRepresentation> apiResponse;
        try {
            LoginCommand command = this.authenticationAndConvert(request, LoginCommand.class);
            ApiUserRepresentation userRepresentation = userAppService.weChatLogin(command);

            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(userRepresentation.getId(), LoggerType.APP_LOGGER,
                    userRepresentation.getUserName() + "登陆成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, userRepresentation);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (AccountException | ExcessiveAttemptsException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_ACCOUNT_EXCEPTION);
        } catch (LockedAccountException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_ACCOUNT_LOCKED);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/info")
    public void info(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<ApiUserRepresentation> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            ApiUserRepresentation userRepresentation = userAppService.info(command.getString("username"));
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

    @RequestMapping(value = "/bind_invite")
    public void bindInviteCode(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<?> apiResponse;
        try {
            InviteCodeCommand command = this.authenticationAndConvert(request, InviteCodeCommand.class);
            userAppService.bindInviteCode(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS);
        } catch (AuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (InviteCodeException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_INVITECODE_BOUND);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_INVITECODE_USED);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/receive_gold")
    public void receiveGold(HttpServletRequest request, HttpServletResponse response) {

        ApiResponse<BigDecimal> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);
            BigDecimal golds = userAppService.receiveGold(command.getString("userName"));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, golds);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (CountNotEnoughException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_COUNT_NOT_ENOUGH);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/receive_benefit")
    public void receiveBenefit(HttpServletRequest request, HttpServletResponse response) {

        ApiResponse<BigDecimal> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);
            BigDecimal benefits = userAppService.receiveBenefit(command.getString("userName"));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, benefits);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (CountNotEnoughException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_COUNT_NOT_ENOUGH);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
