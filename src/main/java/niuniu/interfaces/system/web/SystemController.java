package niuniu.interfaces.system.web;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.system.ISystemAppService;
import niuniu.application.system.command.EditSystemCommand;
import niuniu.core.enums.LoggerType;
import niuniu.core.util.CoreHttpUtils;
import niuniu.domain.model.system.System;
import niuniu.interfaces.shared.web.AlertMessage;
import niuniu.interfaces.shared.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by pengyi on 2016/3/31.
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ILoggerAppService loggerAppService;

    private final ISystemAppService systemAppService;

    @Autowired
    public SystemController(ILoggerAppService loggerAppService, ISystemAppService systemAppService) {
        this.loggerAppService = loggerAppService;
        this.systemAppService = systemAppService;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("command") EditSystemCommand command, Locale locale) {
        AlertMessage alertMessage;
        System system;
        try {
            system = systemAppService.getSystem();
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/system/edit", "system", system).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditSystemCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/system/edit", "command", command);
        }
        AlertMessage alertMessage;
        System system;
        try {
            system = systemAppService.edit(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "修改System信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("/system/edit", "system", system);
    }

}
