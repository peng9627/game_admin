package niuniu.application.system.command;


import java.math.BigDecimal;

/**
 * Author pengyi
 * Date 16-9-5.
 */
public class EditSystemCommand {

    private String userAgreement;               //用户协议
    private BigDecimal ratio;                   //充值比例
    private int countMultiple;                  //在线人数倍数
    private BigDecimal registerGive;            //注册赠送
    private BigDecimal spreadGive;              //推荐赠送
    private String extensionDomain;             //推广域名
    private String payurl;                      //支付域名
    private String agentGroup;                  //代理QQ群
    private String weChatNumber;                //微信公众号
    private String customerService;             //客服QQ

    public String getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(String userAgreement) {
        this.userAgreement = userAgreement;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public int getCountMultiple() {
        return countMultiple;
    }

    public void setCountMultiple(int countMultiple) {
        this.countMultiple = countMultiple;
    }

    public BigDecimal getRegisterGive() {
        return registerGive;
    }

    public void setRegisterGive(BigDecimal registerGive) {
        this.registerGive = registerGive;
    }

    public BigDecimal getSpreadGive() {
        return spreadGive;
    }

    public void setSpreadGive(BigDecimal spreadGive) {
        this.spreadGive = spreadGive;
    }

    public String getExtensionDomain() {
        return extensionDomain;
    }

    public void setExtensionDomain(String extensionDomain) {
        this.extensionDomain = extensionDomain;
    }

    public String getPayurl() {
        return payurl;
    }

    public void setPayurl(String payurl) {
        this.payurl = payurl;
    }

    public String getAgentGroup() {
        return agentGroup;
    }

    public void setAgentGroup(String agentGroup) {
        this.agentGroup = agentGroup;
    }

    public String getWeChatNumber() {
        return weChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        this.weChatNumber = weChatNumber;
    }

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }
}
