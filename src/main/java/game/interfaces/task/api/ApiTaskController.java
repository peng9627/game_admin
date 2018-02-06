package game.interfaces.task.api;

import game.application.task.ITaskAppService;
import game.application.task.representation.TaskRepresentation;
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
 * Date : 17-9-25.
 * desc:
 */
@Controller
@RequestMapping("/api/task")
public class ApiTaskController extends BaseApiController {

    private final ITaskAppService taskAppService;

    @Autowired
    public ApiTaskController(ITaskAppService taskAppService) {
        this.taskAppService = taskAppService;
    }

    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response) {
        ApiResponse apiResponse;
        try {
            List<TaskRepresentation> tasks = taskAppService.list();
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, tasks);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }
}
