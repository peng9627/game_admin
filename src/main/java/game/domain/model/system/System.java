package game.domain.model.system;

import game.core.id.ConcurrencySafeEntity;

import java.math.BigDecimal;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
public class System extends ConcurrencySafeEntity {

    private int registerGive;           //注册送
    private int shareGive;              //分享送
    private BigDecimal rechargeRatio;          //充值比例

    public int getRegisterGive() {
        return registerGive;
    }

    public void setRegisterGive(int registerGive) {
        this.registerGive = registerGive;
    }

    public int getShareGive() {
        return shareGive;
    }

    public void setShareGive(int shareGive) {
        this.shareGive = shareGive;
    }

    public BigDecimal getRechargeRatio() {
        return rechargeRatio;
    }

    public void setRechargeRatio(BigDecimal rechargeRatio) {
        this.rechargeRatio = rechargeRatio;
    }
}
