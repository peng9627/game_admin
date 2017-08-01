package niuniu.interfaces.gamerecord.web;

import niuniu.application.gamerecord.IGameRecordAppService;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.application.gamerecord.representation.GameRecordRepresentation;
import niuniu.core.exception.NoFoundException;
import niuniu.interfaces.shared.web.AlertMessage;
import niuniu.interfaces.shared.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;


/**
 * 游戏记录 WEB Controller层
 * Created by zhangjin on 2017/6/1.
 */
@Controller
@RequestMapping("/gamerecord")
public class GameRecordController extends BaseController {

    private final IGameRecordAppService gameRecordAppService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GameRecordController(IGameRecordAppService gameRecordAppService) {
        this.gameRecordAppService = gameRecordAppService;
    }

    /**
     * 分页（带条件查询）
     *
     * @param command 查询条件
     * @return ModelAndView
     */
    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListGameRecordCommand command) {

        ModelAndView modelAndView = new ModelAndView("/gamerecord/list", "pagination", gameRecordAppService.pagination(command))
                .addObject("command", command);
        return modelAndView;
    }

    /**
     * 获取游戏记录详细信息
     *
     * @param id 记录ID
     * @return ModelAndView
     */
    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;
        GameRecordRepresentation gameRecord;
        try {
            gameRecord = gameRecordAppService.searchById(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("gameRecord.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/gamerecord/pagination");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("/gamerecord/info", "gameRecord", gameRecord);
    }

}
