package game.interfaces.user.web;

import game.application.account.representation.AccountRepresentation;
import game.application.logger.ILoggerAppService;
import game.application.logger.command.CreateLoggerCommand;
import game.application.recharge.IRechargeAppService;
import game.application.recharge.command.ListRechargeCommand;
import game.application.shared.command.SharedCommand;
import game.application.system.ISystemAppService;
import game.application.user.IUserAppService;
import game.application.user.command.CreateUserCommand;
import game.application.user.command.EditUserCommand;
import game.application.user.command.ListUserCommand;
import game.application.user.command.MoneyCommand;
import game.application.user.representation.UserRepresentation;
import game.core.common.Constants;
import game.core.enums.LoggerType;
import game.core.exception.ConcurrencyException;
import game.core.exception.ExistException;
import game.core.exception.NoFoundException;
import game.core.util.CoreHttpUtils;
import game.core.util.CoreStringUtils;
import game.domain.model.system.System;
import game.interfaces.shared.web.AlertMessage;
import game.interfaces.shared.web.BaseController;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IUserAppService userAppService;
    private final ISystemAppService systemAppService;

    private final IRechargeAppService rechargeAppService;

    private final ILoggerAppService loggerAppService;


    @Autowired
    public UserController(IUserAppService userAppService, ISystemAppService systemAppService, IRechargeAppService rechargeAppService, ILoggerAppService loggerAppService) {
        this.userAppService = userAppService;
        this.systemAppService = systemAppService;
        this.rechargeAppService = rechargeAppService;
        this.loggerAppService = loggerAppService;
    }

    @RequestMapping(value = "/children")
    public ModelAndView children(ListUserCommand command, Locale locale) {
        if (CoreStringUtils.isEmpty(command.getParent())) {
            AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.account.NotPermission.messages", null, locale));
            return new ModelAndView("/user/children", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage)
                    .addObject("command", command);
        }
        System system = systemAppService.getSystem();
        return new ModelAndView("/user/children", "pagination", userAppService.pagination(command))
                .addObject("command", command).addObject("url", system.getExtensionDomain());
    }

    @RequestMapping(value = "/agent")
    public ModelAndView agent(HttpSession session) {
        AccountRepresentation command;
        if (null != session.getAttribute(Constants.SESSION_USER)) {
            command = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
        } else {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("/user/agent", "command", command);
    }

    @RequestMapping(value = "/agent_children")
    public ModelAndView agent_children(ListUserCommand command, Locale locale) {
        if (CoreStringUtils.isEmpty(command.getParent())) {
            AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.account.NotPermission.messages", null, locale));

            return new ModelAndView("/user/agent_children", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage)
                    .addObject("command", command);
        }
        return new ModelAndView("/user/agent_children", "pagination", userAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/agent_recharge")
    public ModelAndView agent_index(ListRechargeCommand command) {

        return new ModelAndView("/user/agent_recharge", "pagination", rechargeAppService.paginationEq(command))
                .addObject("command", command).addObject("totalMoney", rechargeAppService.totalMoneyEq(command));
    }

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListUserCommand command) {
        return new ModelAndView("/user/list", "pagination", userAppService.pagination(command))
                .addObject("command", command).addObject("totalMoney", userAppService.totalMoney(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateUserCommand command) {
        return new ModelAndView("/user/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateUserCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/create");
        }
        AlertMessage alertMessage;
        UserRepresentation user;
        try {
            user = userAppService.create(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("role.id.not.found.messages", new Object[]{command.getRoles()}, locale));
            return new ModelAndView("/user/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("account.userName.Exist.messages", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/user/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "创建User[" + user.getUserName() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", user.getId());
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        UserRepresentation user;
        String url = request.getHeader("Referer");
        if (null == url || !url.contains("user_approve")) {
            url = "/user/pagination";
        } else {
            url = "/user/user_approve";
        }
        try {
            user = userAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/user/info", "user", user).addObject("url", url);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditUserCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        UserRepresentation user;
        try {
            user = userAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/user/edit", "user", user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditUserCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/edit", "command", command);
        }
        AlertMessage alertMessage;
        UserRepresentation user;
        try {
            user = userAppService.edit(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "修改[" + user.getUserName() + "]信息成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", user.getId());
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/add_money", method = RequestMethod.POST)
    public ModelAndView addMoney(@Valid @ModelAttribute("command") MoneyCommand command, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.parameter.error", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        }

        try {
            userAppService.addMoney(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "给ID[" + command.getId() + "]添加余额[" + command.getMoney().toString() + "]成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/subtract_money", method = RequestMethod.POST)
    public ModelAndView subtractMoney(@Valid @ModelAttribute("command") MoneyCommand command, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.parameter.error", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        }

        try {
            userAppService.subtractMoney(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "给ID[" + command.getId() + "]扣余额[" + command.getMoney().toString() + "]成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/update_vip")
    public ModelAndView updateVip(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        AlertMessage alertMessage;
        try {
            userAppService.updateVip(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "修改Account[" + command.getId() + "]Vip状态成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/pagination");
    }

    @RequestMapping(value = "/register/{id}", method = RequestMethod.GET)
    public ModelAndView register(@PathVariable String id, @ModelAttribute("command") CreateUserCommand command, Locale locale) {
        AlertMessage alertMessage;
        try {
            command.setParent(id);
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/user/register", "command", command);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(CreateUserCommand command, HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setContentType("text/plain; charset=utf-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Max-Age", "100");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Allow-Credentials", "false");
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                response.getWriter().write("用户名不能为空");
                return;
            }
            if (6 > command.getUserName().length() || 12 < command.getUserName().length()) {
                response.getWriter().write("用户名长度不合法");
                return;
            }
            if (command.getUserName().startsWith("robot") || !Pattern.compile("[A-Za-z0-9_-]+$").matcher(command.getUserName()).find()) {
                response.getWriter().write("非法用户名");
                return;
            }
            try {
                userAppService.create(command);
            } catch (ExistException e) {
                response.getWriter().write("用户名已存在");
                logger.warn(e.getMessage());
                return;
            }
            AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
            if (sessionUser != null) {
                CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                        "创建User[" + command.getUserName() + "]成功", CoreHttpUtils.getClientIP(request));
                loggerAppService.create(loggerCommand);
            }
            response.getWriter().write("处理成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/register_web", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("command") CreateUserCommand command, BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.parameter.error", null, locale));
            return new ModelAndView("/user/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        try {
            userAppService.create(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.id.not.found.messages", new Object[]{command.getParent()}, locale));
            return new ModelAndView("/user/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("account.userName.Exist.messages", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/user/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        AccountRepresentation sessionUser = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            CreateLoggerCommand loggerCommand = new CreateLoggerCommand(sessionUser.getId(), LoggerType.OPERATION,
                    "创建User[" + command.getUserName() + "]成功", CoreHttpUtils.getClientIP(request));
            loggerAppService.create(loggerCommand);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        return new ModelAndView("/user/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }

}
