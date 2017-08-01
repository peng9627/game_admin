package niuniu.interfaces.recharge.web;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.recharge.IRechargeGiveAppService;
import niuniu.application.recharge.command.CreateRechargeGiveCommand;
import niuniu.core.common.BasicPaginationCommand;
import niuniu.core.enums.LoggerType;
import niuniu.core.util.CoreHttpUtils;
import niuniu.interfaces.shared.web.AlertMessage;
import niuniu.interfaces.shared.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Controller
@RequestMapping("/recharge_give")
public class RechargeGiveController extends BaseController {

    private final IRechargeGiveAppService rechargeGiveAppService;

    private final ILoggerAppService loggerAppService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RechargeGiveController(IRechargeGiveAppService rechargeGiveAppService, ILoggerAppService loggerAppService) {
        this.rechargeGiveAppService = rechargeGiveAppService;
        this.loggerAppService = loggerAppService;
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(BasicPaginationCommand command) {
        return new ModelAndView("/recharge_give/list", "pagination", rechargeGiveAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateRechargeGiveCommand command) {
        return new ModelAndView("/recharge_give/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateRechargeGiveCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/recharge_give/create", "command", command);
        }
        AlertMessage alertMessage;
        try {
            rechargeGiveAppService.create(command);
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "创建RechargeGive[" + command.getGiveMoney() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/recharge_give/pagination");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        rechargeGiveAppService.delete(id);
        AlertMessage alertMessage = new AlertMessage(this.getMessage("default.delete.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/recharge_give/pagination");
    }
}
