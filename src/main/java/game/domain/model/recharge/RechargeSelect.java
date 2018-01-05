package game.domain.model.recharge;

import game.core.id.ConcurrencySafeEntity;

import java.math.BigDecimal;

/**
 * Created by pengyi
 * Date : 17-12-9.
 * desc:
 */
public class RechargeSelect extends ConcurrencySafeEntity {

    private int type;
    private BigDecimal price;
    private int currency;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
