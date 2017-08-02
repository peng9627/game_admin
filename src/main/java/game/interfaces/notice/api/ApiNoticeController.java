package game.interfaces.notice.api;

import game.application.notice.INoticeAppService;
import game.application.notice.representation.ApiNotice;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Author pengyi
 * Date 17-5-27.
 */
@Controller
@RequestMapping("/api/notice")
public class ApiNoticeController extends BaseApiController {

    private final INoticeAppService noticeAppService;

    @Autowired
    public ApiNoticeController(INoticeAppService noticeAppService) {
        this.noticeAppService = noticeAppService;
    }

    @RequestMapping(value = "/get")
    public void get(HttpServletResponse response) {
        ApiResponse<ApiNotice> apiResponse;
        try {
            ApiNotice apiNotice = noticeAppService.apiGet();
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, apiNotice);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
