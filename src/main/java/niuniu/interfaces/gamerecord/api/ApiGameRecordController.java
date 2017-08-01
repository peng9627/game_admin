package niuniu.interfaces.gamerecord.api;

import niuniu.application.gamerecord.IGameRecordAppService;
import niuniu.application.gamerecord.command.CreateGameRecordCommand;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.application.gamerecord.representation.ApiGameRecordRepresentation;
import niuniu.core.api.ApiResponse;
import niuniu.core.api.ApiReturnCode;
import niuniu.core.exception.ApiAuthenticationException;
import niuniu.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 游戏记录 Api Controller
 * Created by zhangjin on 2017/6/2.
 */
@Controller
@RequestMapping("/api/gamerecord")
public class ApiGameRecordController extends BaseApiController {

    private final IGameRecordAppService gameRecordAppService;

    @Autowired
    public ApiGameRecordController(IGameRecordAppService gameRecordAppService) {
        this.gameRecordAppService = gameRecordAppService;
    }


    /**
     * 创建游戏记录
     *
     * @param request  Request
     * @param response Response
     */
    @RequestMapping(value = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<CreateGameRecordCommand> apiResponse;
        try {
            CreateGameRecordCommand command = this.authenticationAndConvert(request, CreateGameRecordCommand.class);
            gameRecordAppService.create(command);
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

    /**
     * 获取游戏记录列表
     *
     * @param request  Request
     * @param response Response
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) {

        ApiResponse<List<ApiGameRecordRepresentation>> apiResponse;
        try {
            ListGameRecordCommand command = this.authenticationAndConvert(request, ListGameRecordCommand.class);
            List<ApiGameRecordRepresentation> list = gameRecordAppService.list(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, list);
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
