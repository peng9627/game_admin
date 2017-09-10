package game.interfaces.goods.api;

import game.application.goods.IGoodsAppService;
import game.application.goods.representation.GoodsRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Controller
@RequestMapping("/api/goods")
public class ApiGoodsController extends BaseApiController {

    private final IGoodsAppService goodsAppService;

    @Autowired
    public ApiGoodsController(IGoodsAppService goodsAppService) {
        this.goodsAppService = goodsAppService;
    }

    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response) {
        ApiResponse<List<GoodsRepresentation>> apiResponse;
        try {
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, goodsAppService.list());
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
