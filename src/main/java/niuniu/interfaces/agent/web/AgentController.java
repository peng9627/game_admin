package niuniu.interfaces.agent.web;

import niuniu.application.account.representation.AccountRepresentation;
import niuniu.application.agent.IAgentAppService;
import niuniu.application.auth.IAuthAppService;
import niuniu.application.auth.command.LoginCommand;
import niuniu.application.logger.ILoggerAppService;
import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.core.common.Constants;
import niuniu.core.enums.LoggerType;
import niuniu.core.exception.AccountException;
import niuniu.core.exception.ExistException;
import niuniu.core.util.CoreHttpUtils;
import niuniu.interfaces.shared.web.AlertMessage;
import niuniu.interfaces.shared.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by zhangjin on 2017/6/23.
 */
@Controller
public class AgentController extends BaseController {

    private final IAuthAppService authAppService;

    private final IAgentAppService agentAppService;

    private final ILoggerAppService loggerAppService;

    @Autowired
    public AgentController(IAuthAppService authAppService, IAgentAppService agentAppService, ILoggerAppService loggerAppService) {
        this.authAppService = authAppService;
        this.agentAppService = agentAppService;
        this.loggerAppService = loggerAppService;
    }

    @RequestMapping(value = "/agent")
    public ModelAndView index() {
        return new ModelAndView("/agent/index", "command", new niuniu.application.agent.command.LoginCommand());
    }

    @RequestMapping(value = "/agent/main", method = RequestMethod.GET)
    public ModelAndView login(LoginCommand command) {
        return new ModelAndView("/agent/index", "command", command);
    }

    @RequestMapping(value = "/agent/main", method = RequestMethod.POST)
    public ModelAndView login(LoginCommand command, HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        try {
            boolean flag = command.getVerificationCode() == (int) session.getAttribute("code");
            if (!flag) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.verificationCode.Error.messages", null, locale));
                return new ModelAndView("/agent/index", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            }
            //验证码正确
            command.setLoginIP(CoreHttpUtils.getClientIP(request));
            command.setLoginPlatform(CoreHttpUtils.getLoginPlatform(request));
            AccountRepresentation user = authAppService.login(command);
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole("agent")) {
                session.setAttribute(Constants.SESSION_USER, user);
                return new ModelAndView("/agent/my", "agent", agentAppService.getAgentInfo(user.getUserName()));
            } else {//不是代理用户
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.account.NotPermission.messages", null, locale));
                return new ModelAndView("/agent/index", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            }
        } catch (UnknownAccountException ue) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.NotExists.messages", null, locale));
        } catch (AccountException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.NotPermission.messages", null, locale));
        } catch (IncorrectCredentialsException ie) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.Error.messages", null, locale));
        } catch (LockedAccountException le) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.Disable.messages", null, locale));
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER, this.getMessage("login.login.Failure.messages", null, locale));
        }
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/agent");
    }

    @RequestMapping(value = "/agent/weChat_bind")
    public ModelAndView bind(String userName, String weChatNo, HttpServletRequest request, Locale locale) {

        try {
            AccountRepresentation user = (AccountRepresentation) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");

            if (user != null && SecurityUtils.getSubject().hasRole("agent")) {
                //同一个session操作
                if (userName != null && weChatNo != null && userName.equals(user.getUserName())) {
                    agentAppService.weChatBind(weChatNo, userName);
                    CreateLoggerCommand loggerCommand = new CreateLoggerCommand(user.getId(), LoggerType.OPERATION,
                            "绑定[" + user.getId() + "]微信成功", CoreHttpUtils.getClientIP(request));
                    loggerAppService.create(loggerCommand);
                    return new ModelAndView("/agent/my", "agent", agentAppService.getAgentInfo(userName))
                            .addObject(new AlertMessage(AlertMessage.MessageType.SUCCESS, this.getMessage("agent.bind.success.messages", null, locale)));
                }
                if (userName != null && !userName.equals(user.getUserName())) {//原session失效
                    AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("agent.session.invalid.messages", null, locale));
                    return new ModelAndView("/agent/index").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                }
            }
        } catch (ExistException xe) {
            return new ModelAndView("/agent/my", "agent", agentAppService.getAgentInfo(userName))
                    .addObject(new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("agent.bind.repeat.messages", null, locale)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //绑定异常
        AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("agent.bind.exception.messages", null, locale));
        return new ModelAndView("/agent/index").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }
}
