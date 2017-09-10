package game.interfaces.exchange.api;

import game.application.exchange.IExchangeAppService;
import game.application.exchange.representation.ExchangeRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Controller
@RequestMapping("/api/exchange")
public class ApiExchangeController extends BaseApiController {

    private final IExchangeAppService exchangeAppService;

    @Autowired
    public ApiExchangeController(IExchangeAppService exchangeAppService) {
        this.exchangeAppService = exchangeAppService;
    }

    @RequestMapping(value = "/list/{userId}")
    public void list(@PathVariable String userId, HttpServletResponse response) {
        ApiResponse<List<ExchangeRepresentation>> apiResponse;
        try {
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, exchangeAppService.list(userId));
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
