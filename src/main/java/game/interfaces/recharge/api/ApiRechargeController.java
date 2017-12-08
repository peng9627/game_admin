package game.interfaces.recharge.api;

import game.application.recharge.IRechargeAppService;
import game.application.recharge.command.ListRechargeCommand;
import game.application.recharge.representation.ApiRechargeRepresentation;
import game.core.api.ApiResponse;
import game.core.api.ApiReturnCode;
import game.core.enums.PayType;
import game.core.exception.ApiAuthenticationException;
import game.core.pay.wechat.Signature;
import game.core.pay.wechat.WechatNotify;
import game.core.pay.wechat.XMLParser;
import game.interfaces.shared.api.BaseApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * 充值记录api接口
 * Created by zhangjin on 2017/6/6.
 */
@Controller
@RequestMapping("/api/recharge")
public class ApiRechargeController extends BaseApiController {

    private final IRechargeAppService rechargeAppService;

    @Autowired
    public ApiRechargeController(IRechargeAppService rechargeAppService) {
        this.rechargeAppService = rechargeAppService;
    }

    @RequestMapping(value = "/list")
    public void pagination(HttpServletRequest request, HttpServletResponse response) {

        ApiResponse<List> apiResponse;
        try {
            ListRechargeCommand command = this.authenticationAndConvert(request, ListRechargeCommand.class);
            List<ApiRechargeRepresentation> pagination = rechargeAppService.list(command);
            apiResponse = new ApiResponse<>(ApiReturnCode.SUCCESS, pagination);

        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse<>(ApiReturnCode.AUTHENTICATION_FAILURE);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>(ApiReturnCode.ERROR_UNKNOWN);
        }
        this.returnData(response, apiResponse);
    }

    @RequestMapping(value = "/wechat/notify")
    public String rechargeWecatNotify(HttpServletRequest request, Locale locale) {
        int contentLength = request.getContentLength();
        byte[] bytes = new byte[contentLength];
        WechatNotify notify = null;
        try {
            request.getInputStream().read(bytes, 0, contentLength);
            notify = (WechatNotify) XMLParser.getObjFromXML(new String(bytes), WechatNotify.class);
            logger.info("response---------------->" + new String(bytes, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sign = null;
        if (notify != null) {
            sign = notify.getSign();
            notify.setSign("");
            try {
                String mySign = Signature.getWechatSign(notify);
                if (mySign.equals(sign)) {
                    if (notify.getReturn_code().equals("SUCCESS")) {
                        if (notify.getResult_code().equals("SUCCESS")) {
                            rechargeAppService.wechatSuccess(notify);
                            logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付成功，支付方式为[" + PayType.WECHAT + "]");
                            return "<xml>\n" +
                                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                                    "</xml>";
                        } else {
                            logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[" + notify.getErr_code_des() + "]");
                        }

                    } else {
                        logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[" + notify.getReturn_msg() + "]");
                    }
                } else {
                    logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[{1}]");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "<xml>\n" +
                "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                "  <return_msg><![CDATA[错误]]></return_msg>\n" +
                "</xml>";
    }

}
