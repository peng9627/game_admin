package game.interfaces.notice.api;

import com.alibaba.fastjson.JSON;
import game.application.goods.IGoodsAppService;
import game.application.goods.representation.GoodsRepresentation;
import game.core.exception.ApiAuthenticationException;
import game.core.redis.RedisService;
import game.domain.model.notice.command.NoticeCommand;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-7.
 * desc:
 */
@Controller
@RequestMapping("/api/notice")
public class ApiNoticeController extends BaseApiController {

    private final IGoodsAppService goodsAppService;
    private final RedisService redisService;

    @Autowired
    public ApiNoticeController(IGoodsAppService goodsAppService, RedisService redisService) {
        this.goodsAppService = goodsAppService;
        this.redisService = redisService;
    }

    @RequestMapping(value = "/notice")
    @ResponseBody
    public void notice(HttpServletRequest request) {

        try {
            NoticeCommand command = this.authenticationAndConvert(request, NoticeCommand.class);
            switch (command.getNoticeType()) {
                case GOODS:
                    List<GoodsRepresentation> goodsRepresentations = goodsAppService.list();
                    int version;
                    if (redisService.exists("cache_goods")) {
                        String goodsCache = redisService.getCache("cache_goods");
                        String[] version_cache = goodsCache.split(",");
                        version = Integer.parseInt(version_cache[0]) + 1;
                    } else {
                        version = 1;
                    }
                    redisService.addCache("cache_goods", version + "," + JSON.toJSONString(goodsRepresentations));
                    break;
            }
            //TODO 通知游戏服务器修改商城
        } catch (ApiAuthenticationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
