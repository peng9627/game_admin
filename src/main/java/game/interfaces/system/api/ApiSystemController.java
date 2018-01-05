package game.interfaces.system.api;

import com.alibaba.fastjson.JSONObject;
import game.application.recharge.IRechargeSelectAppService;
import game.application.system.ISystemAppService;
import game.application.system.representation.SystemRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private final IRechargeSelectAppService rechargeSelectAppService;

    @Autowired
    public ApiSystemController(ISystemAppService systemAppService, IRechargeSelectAppService rechargeSelectAppService) {
        this.systemAppService = systemAppService;
        this.rechargeSelectAppService = rechargeSelectAppService;
    }

    @RequestMapping(value = "/info")
    public void info(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<SystemRepresentation> apiResponse;
        try {
            SystemRepresentation systemRepresentation = systemAppService.info();
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, systemRepresentation);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/recharge_ratio")
    @ResponseBody
    public JSONObject rechargeRatio() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        try {
            SystemRepresentation systemRepresentation = systemAppService.info();
            jsonObject.put("code", 0);
            jsonObject.put("rechargeRatio", systemRepresentation.getRechargeRatio());
            jsonObject.put("rechargeSelect", rechargeSelectAppService.list());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
