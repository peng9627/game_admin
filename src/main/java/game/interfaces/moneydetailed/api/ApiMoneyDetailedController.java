package game.interfaces.moneydetailed.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import game.application.moneydetailed.IMoneyDetailedAppService;
import game.application.moneydetailed.command.CreateCommand;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.api.SocketRequest;
import game.core.enums.NoticeType;
import game.core.exception.ApiAuthenticationException;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

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
            SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullBooleanAsFalse};
            int ss = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
            SocketRequest socketRequest = new SocketRequest();
            socketRequest.setNoticeType(NoticeType.CURRENCY);
            socketRequest.setUserId(command.getUserId());
            byte[] bytes = JSON.toJSONBytes(socketRequest, ss, features);
            Socket socket = new Socket("127.0.0.1", 10010);
            OutputStream os = socket.getOutputStream();
            writeInt(os, bytes.length);
            os.write(bytes);
            os.flush();
            os.close();
            socket.close();

        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    private void writeInt(OutputStream s, int v) throws IOException {
        s.write((v >>> 24) & 0xFF);
        s.write((v >>> 16) & 0xFF);
        s.write((v >>> 8) & 0xFF);
        s.write((v) & 0xFF);
    }
}
