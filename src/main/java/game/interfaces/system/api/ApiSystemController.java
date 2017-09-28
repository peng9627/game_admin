package game.interfaces.system.api;

import com.alibaba.fastjson.JSONObject;
import game.application.system.ISystemAppService;
import game.application.system.representation.SystemRepresentation;
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
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Controller
@RequestMapping("/api/system")
public class ApiSystemController extends BaseApiController {

    private final ISystemAppService systemAppService;

    @Autowired
    public ApiSystemController(ISystemAppService systemAppService) {
        this.systemAppService = systemAppService;
    }

    @RequestMapping(value = "/info")
    public void info(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<SystemRepresentation> apiResponse;
        try {
            JSONObject command = this.authenticationAndConvert(request, JSONObject.class);

            SystemRepresentation systemRepresentation = systemAppService.info(command.getInteger("userId"));
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, systemRepresentation);
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
