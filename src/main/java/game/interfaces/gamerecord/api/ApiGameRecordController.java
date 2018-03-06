package game.interfaces.gamerecord.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import game.application.gamerecord.IGameRecordAppService;
import game.application.gamerecord.command.CreateCommand;
import game.application.gamerecord.command.ListCommand;
import game.application.gamerecord.representation.GameRecordInfoRepresentation;
import game.application.gamerecord.representation.GameRecordRepresentation;
import game.application.user.IUserAppService;
import game.application.user.representation.UserRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.api.SocketRequest;
import game.core.exception.ApiAuthenticationException;
import game.core.exception.NoFoundException;
import game.core.util.CoreHttpUtils;
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
    private final IUserAppService userAppService;

    @Autowired
    public ApiGameRecordController(IGameRecordAppService gameRecordAppService, IUserAppService userAppService) {
        this.gameRecordAppService = gameRecordAppService;
        this.userAppService = userAppService;
    }

    @RequestMapping(value = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        ApiResponse apiResponse;
        try {
            CreateCommand command = this.authenticationAndConvert(request, CreateCommand.class);

            gameRecordAppService.create(command);
            apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);

            String[] peoples = command.getPeople().split(",");
            for (String s : peoples) {
                UserRepresentation user = userAppService.info(Integer.valueOf(s));
                if (null != user && null != user.getParentId()) {
                    SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty,
                            SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                            SerializerFeature.WriteNullBooleanAsFalse};
                    int ss = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
                    SocketRequest socketRequest = new SocketRequest();
                    socketRequest.setUserId(user.getParentId());
                    CoreHttpUtils.urlConnectionByRsa("http://127.0.0.1:10110/1", JSON.toJSONString(socketRequest, ss, features));
                }
            }

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
