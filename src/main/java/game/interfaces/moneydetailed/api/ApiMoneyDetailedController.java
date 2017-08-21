package game.interfaces.moneydetailed.api;

import game.application.moneydetailed.IMoneyDetailedAppService;
import game.application.moneydetailed.command.CreateCommand;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.exception.ApiAuthenticationException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author pengyi
 * Date 17-5-27.
 */
@Controller
@RequestMapping("/api/money_detailed")
public class ApiMoneyDetailedController extends BaseApiController {

    private final IMoneyDetailedAppService moneyDetailedAppService;

    @Autowired
    public ApiMoneyDetailedController(IMoneyDetailedAppService moneyDetailedAppService) {
        this.moneyDetailedAppService = moneyDetailedAppService;
    }

    @RequestMapping(value = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse apiResponse;
        try {
            CreateCommand command = this.authenticationAndConvert(request, CreateCommand.class);

            moneyDetailedAppService.create(command);
            apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

}
