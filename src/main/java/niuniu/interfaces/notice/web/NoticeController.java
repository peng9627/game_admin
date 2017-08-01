package niuniu.interfaces.notice.web;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.notice.INoticeAppService;
import niuniu.application.notice.command.CreateNoticeCommand;
import niuniu.application.notice.command.EditNoticeCommand;
import niuniu.application.notice.command.ListNoticeCommand;
import niuniu.core.enums.LoggerType;
import niuniu.core.exception.ConcurrencyException;
import niuniu.core.exception.NoFoundException;
import niuniu.core.util.CoreHttpUtils;
import niuniu.domain.model.notice.Notice;
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
 * Author pengyi
 * Date 16-9-6.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private final ILoggerAppService loggerAppService;

    private final INoticeAppService noticeAppService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public NoticeController(ILoggerAppService loggerAppService, INoticeAppService noticeAppService) {
        this.loggerAppService = loggerAppService;
        this.noticeAppService = noticeAppService;
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListNoticeCommand command) {
        return new ModelAndView("/notice/list", "pagination", noticeAppService.list(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateNoticeCommand command) {
        return new ModelAndView("/notice/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(CreateNoticeCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/notice/create", "command", command);
        }
        AlertMessage alertMessage;
        try {
            noticeAppService.create(command);
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "创建Notice[" + command.getContent() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/notice/pagination");
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable String id, Locale locale) {
        AlertMessage alertMessage;
        try {
            noticeAppService.delete(id);
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.delete.success.messages", null, locale));
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("redirect:/notice/pagination").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }


    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") CreateNoticeCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        Notice notice;
        try {
            notice = noticeAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("notice.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/notice/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/notice/edit", "notice", notice).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditNoticeCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/notice/edit", "command", command);
        }
        AlertMessage alertMessage;
        Notice notice;
        try {
            notice = noticeAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getTitle()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/notice/edit/{id}");
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("notice.id.not.found.messages", new Object[]{command.getId()}, locale));
            return new ModelAndView("/notice/edit", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "修改Notice[" + notice.getTitle() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/notice/pagination");
    }
}
