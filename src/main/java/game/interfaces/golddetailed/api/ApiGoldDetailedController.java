package game.interfaces.golddetailed.api;

import game.application.golddetailed.IGoldDetailedAppService;
import game.application.golddetailed.command.CreateGoldDetailedCommand;
import game.application.user.representation.ApiUserRepresentation;
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
@RequestMapping("/api/gold_detailed")
public class ApiGoldDetailedController extends BaseApiController {

    private final IGoldDetailedAppService goldDetailedAppService;

    @Autowired
    public ApiGoldDetailedController(IGoldDetailedAppService goldDetailedAppService) {
        this.goldDetailedAppService = goldDetailedAppService;
    }

    @RequestMapping(value = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<ApiUserRepresentation> apiResponse;
        try {
            CreateGoldDetailedCommand command = this.authenticationAndConvert(request, CreateGoldDetailedCommand.class);

            goldDetailedAppService.create(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS);
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