package game.interfaces.gamerecord.api;

import game.application.gamerecord.IGameRecordAppService;
import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.application.gamerecord.representation.GameRecordInfoRepresentation;
import game.application.gamerecord.representation.GameRecordRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.exception.ApiAuthenticationException;
import game.core.exception.NoFoundException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-21.
 * desc:
 */
@Controller()
@RequestMapping("/api/gamerecord")
public class ApiGameRecordController extends BaseApiController {

    private final IGameRecordAppService gameRecordAppService;

    @Autowired
    public ApiGameRecordController(IGameRecordAppService gameRecordAppService) {
        this.gameRecordAppService = gameRecordAppService;
    }

    @RequestMapping(value = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse apiResponse;
        try {
            CreateCommand command = this.authenticationAndConvert(request, CreateCommand.class);

            gameRecordAppService.create(command);
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

    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<List<GameRecordRepresentation>> apiResponse;
        try {
            ListCommand command = this.authenticationAndConvert(request, ListCommand.class);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, gameRecordAppService.list(command));
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/info/{id}")
    public void list(@PathVariable String id, HttpServletResponse response) {
        ApiResponse<GameRecordInfoRepresentation> apiResponse;
        try {
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, gameRecordAppService.info(id));
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.NO_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
