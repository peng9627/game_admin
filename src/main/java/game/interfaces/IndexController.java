package game.interfaces;

import game.application.user.IUserAppService;
import game.application.user.representation.UserRepresentation;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Author pengyi
 * Date 17-5-25.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseApiController {

    private final IUserAppService userAppService;

    @Autowired
    public IndexController(IUserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpSession session) {
        UserRepresentation userRepresentation = null;
        try {
            if (null != session) {
                Integer userId = (Integer) session.getAttribute("userId");
                if (null != userId) {
                    userRepresentation = userAppService.info(userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/index", "userinfo", userRepresentation);
    }
}
