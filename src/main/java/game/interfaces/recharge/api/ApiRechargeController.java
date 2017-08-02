package game.interfaces.recharge.api;

import game.application.recharge.IRechargeAppService;
import game.application.recharge.command.ListRechargeCommand;
import game.application.recharge.representation.ApiRechargeRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.exception.ApiAuthenticationException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 充值记录api接口
 * Created by zhangjin on 2017/6/6.
 */
@Controller
@RequestMapping("/api/recharge")
public class ApiRechargeController extends BaseApiController {

    private final IRechargeAppService rechargeAppService;

    @Autowired
    public ApiRechargeController(IRechargeAppService rechargeAppService) {
        this.rechargeAppService = rechargeAppService;
    }

    @RequestMapping(value = "/list")
    public void pagination(HttpServletRequest request, HttpServletResponse response) {

        ApiResponse<List> apiResponse;
        try {
            ListRechargeCommand command = this.authenticationAndConvert(request, ListRechargeCommand.class);
            List<ApiRechargeRepresentation> pagination = rechargeAppService.list(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, pagination);

        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

}
