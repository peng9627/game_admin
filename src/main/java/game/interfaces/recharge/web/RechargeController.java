package game.interfaces.recharge.web;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import game.application.recharge.IRechargeAppService;
import game.application.recharge.command.CreateRechargeCommand;
import game.application.recharge.command.ListRechargeCommand;
import game.core.common.Constants;
import game.core.exception.NoLoginException;
import game.core.pay.wechat.*;
import game.core.util.CoreHttpUtils;
import game.domain.model.recharge.Recharge;
import game.interfaces.shared.web.BaseController;
import game.interfaces.shared.web.JsonMessage;
import jdk.internal.org.xml.sax.SAXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/recharge")
    @ResponseBody
    public JsonMessage recharge(CreateRechargeCommand command, HttpServletRequest request) {

        JsonMessage jsonMessage = new JsonMessage();
        if (null == command.getMoney()) {
            jsonMessage.setCode("1");
            jsonMessage.setMessage("money不能为空");
            return jsonMessage;
        }
        try {
            String ip = CoreHttpUtils.getClientIP(request);
            command.setIp(ip);
            Recharge recharge = rechargeAppService.recharge(command);


            String body = "订单号：" + recharge.getRechargeNo();
            String detail = "描述";
            UnifiedRequest unifiedRequest = new UnifiedRequest(body, detail, recharge.getRechargeNo(), recharge.getMoney().multiply(new BigDecimal(100)).intValue(), ip, Constants.WECHAT_NOTIFY_URL);
            UnifiedResponse unifiedResponse = null;
            WechatPayHandle wechatPayHandle = new WechatPayHandle();
            try {
                String sign = Signature.getSign(unifiedRequest.toMap());
                unifiedRequest.setSign(sign);
                XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
                xStream.alias("xml", UnifiedRequest.class);
                String s = CoreHttpUtils.wechatUnified(Constants.WECHAT_UNIFIED_URL, xStream.toXML(unifiedRequest));
                unifiedResponse = (UnifiedResponse) XMLParser.getObjFromXML(s, UnifiedResponse.class);
                if (unifiedResponse != null) {
                    unifiedResponse.setTime_stamp(System.currentTimeMillis() / 1000);
                    unifiedResponse.setNonce_str(RandomStringGenerator.getRandomStringByLength(16));
                    Map<String, Object> map = new HashMap<>();
                    map.put("appid", unifiedResponse.getAppid());
                    map.put("package", "Sign=WXPay");
                    map.put("partnerid", unifiedResponse.getMch_id());
                    map.put("prepayid", unifiedResponse.getPrepay_id());
                    map.put("noncestr", unifiedResponse.getNonce_str());
                    map.put("timestamp", unifiedResponse.getTime_stamp());
                    unifiedResponse.setSign(Signature.getSign(map));
                    System.out.println(unifiedResponse.getSign());

                    wechatPayHandle.setAppId(unifiedResponse.getAppid());
                    wechatPayHandle.setTimeStamp(unifiedResponse.getTime_stamp() + "");
                    wechatPayHandle.setNonceStr(unifiedResponse.getNonce_str());
                    wechatPayHandle.setPackages("Sign=WXPay");
                    wechatPayHandle.setPrepayId(unifiedResponse.getPrepay_id());
                    wechatPayHandle.setPartnerId(unifiedResponse.getMch_id());
                    wechatPayHandle.setSign(unifiedResponse.getSign());

                    jsonMessage.setMessage("成功");
                    jsonMessage.setCode("0");
                    jsonMessage.setData(wechatPayHandle);
                    return jsonMessage;
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonMessage.setMessage("下单失败");
            jsonMessage.setCode("1");
            return jsonMessage;
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage(e.getMessage());
            return jsonMessage;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage("下单失败");
            return jsonMessage;
        }
    }
}
