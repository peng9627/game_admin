package niuniu.interfaces.golddetailed.web;

import niuniu.application.golddetailed.IGoldDetailedAppService;
import niuniu.application.golddetailed.command.ListGoldDetailedCommand;
import niuniu.interfaces.shared.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Controller
@RequestMapping("/gold_detailed")
public class GoldDetailedController extends BaseController {

    private final IGoldDetailedAppService goldDetailedAppService;

    @Autowired
    public GoldDetailedController(IGoldDetailedAppService goldDetailedAppService) {
        this.goldDetailedAppService = goldDetailedAppService;
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListGoldDetailedCommand command) {
        return new ModelAndView("/golddetailed/list", "pagination", goldDetailedAppService.pagination(command))
                .addObject("command", command);
    }
}

