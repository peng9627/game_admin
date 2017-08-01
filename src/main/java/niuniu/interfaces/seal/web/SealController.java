package niuniu.interfaces.seal.web;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.seal.ISealAppService;
import niuniu.application.seal.command.CreateSealCommand;
import niuniu.application.seal.command.ListSealCommand;
import niuniu.core.enums.LoggerType;
import niuniu.core.exception.ExistException;
import niuniu.core.exception.NoFoundException;
import niuniu.core.util.CoreHttpUtils;
import niuniu.interfaces.shared.web.AlertMessage;
import niuniu.interfaces.shared.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Author pengyi
 * Date 16-11-24.
 */
@Controller
@RequestMapping("/seal")
public class SealController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ISealAppService sealAppService;

    private final ILoggerAppService loggerAppService;

    @Autowired
    public SealController(ISealAppService sealAppService, ILoggerAppService loggerAppService) {
        this.sealAppService = sealAppService;
        this.loggerAppService = loggerAppService;
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListSealCommand command) {
        return new ModelAndView("/seal/list", "pagination", sealAppService.pagination(command))
                .addObject("command", command);
    }

//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView create(@ModelAttribute("command") CreateSealCommand command) {
//        return new ModelAndView("/seal/create", "command", command);
//    }

    @RequestMapping(value = "/create")
    public ModelAndView create(@Valid @ModelAttribute("command") CreateSealCommand command,
                               RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        AlertMessage alertMessage;
        try {
            sealAppService.create(command);
            alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("seal.sealNo.Exist.messages", new Object[]{command.getSealNo()}, locale));
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "创建Seal[" + command.getSealNo() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable String id, Locale locale) {
        AlertMessage alertMessage;
        try {
            sealAppService.delete(id);
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.delete.success.messages", null, locale));
        } catch (NoFoundException e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("seal.id.not.found.messages", null, locale));
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("redirect:/seal/pagination").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }
}
