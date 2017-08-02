package game.interfaces.recharge.web;

import game.application.recharge.IRechargeAppService;
import game.application.recharge.command.ListRechargeCommand;
import game.interfaces.shared.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController extends BaseController {

    private final IRechargeAppService rechargeAppService;

    @Autowired
    public RechargeController(IRechargeAppService rechargeAppService) {
        this.rechargeAppService = rechargeAppService;
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListRechargeCommand command) {
        return new ModelAndView("/recharge/list", "pagination", rechargeAppService.pagination(command))
                .addObject("command", command).addObject("totalMoney", rechargeAppService.totalMoney(command));
    }
}
