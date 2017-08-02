package game.interfaces.system.api;

import game.application.system.ISystemAppService;
import game.application.system.representation.ApiSystemRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * System Api Controller
 * Created by zhangjin on 2017/6/7.
 */
@Controller
@RequestMapping("/api/system")
public class ApiSystemController extends BaseApiController {

    private final ISystemAppService systemAppService;

    @Autowired
    public ApiSystemController(ISystemAppService systemAppService) {
        this.systemAppService = systemAppService;
    }

    @RequestMapping(value = "/get")
    public void getSystem(HttpServletResponse response) {
        ApiResponse<ApiSystemRepresentation> apiResponse;
        try {
            ApiSystemRepresentation system = systemAppService.getApiSystem();
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, system);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

}
