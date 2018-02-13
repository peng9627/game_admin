package game.interfaces;

import game.application.user.IUserAppService;
import game.core.common.Constants;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

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

    //    @RequestMapping(value = "/index")
//    public ModelAndView index(HttpSession session) {
//        UserRepresentation userRepresentation = null;
//        try {
//            if (null != session) {
//                Integer userId = (Integer) session.getAttribute("userId");
//                if (null != userId) {
//                    userRepresentation = userAppService.info(userId);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView("/index", "userinfo", userRepresentation);
//    }
    @RequestMapping(value = "/share/{id}")
    public void index(@PathVariable String id, HttpServletResponse response) {
        try {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb56d7a60de4171bc&redirect_uri=http%3a%2f%2fsongjianghezf.chuangmikeji.com%2fuser%2flogin_wechat&response_type=code&scope=snsapi_userinfo&state=" + id + "#wechat_redirect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
