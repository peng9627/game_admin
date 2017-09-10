package game.interfaces.arena.api;

import game.application.arena.IArenaAppService;
import game.application.arena.representation.ArenaRepresentation;
import game.application.arena.representation.command.ListCommand;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.exception.ApiAuthenticationException;
import game.core.exception.NoFoundException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-29.
 * desc:
 */
@Controller()
@RequestMapping("/api/arena")
public class ApiArenaController extends BaseApiController {

    private final IArenaAppService arenaAppService;

    public ApiArenaController(IArenaAppService arenaAppService) {
        this.arenaAppService = arenaAppService;
    }

    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<List<ArenaRepresentation>> apiResponse;
        try {
            ListCommand command = this.authenticationAndConvert(request, ListCommand.class);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, arenaAppService.list(command));
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
        ApiResponse<ArenaRepresentation> apiResponse;
        try {
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, arenaAppService.info(id));
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
