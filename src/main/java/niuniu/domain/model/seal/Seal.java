package niuniu.domain.model.seal;

import niuniu.core.enums.SealType;
import niuniu.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Author pengyi
 * Date 16-11-24.
 */
public class Seal extends ConcurrencySafeEntity {

    private SealType type;
    private String sealNo;

    public SealType getType() {
        return type;
    }

    private void setType(SealType type) {
        this.type = type;
    }

    public String getSealNo() {
        return sealNo;
    }

    private void setSealNo(String sealNo) {
        this.sealNo = sealNo;
    }

    public Seal() {
        setCreateDate(new Date());
    }

    public Seal(SealType type, String sealNo) {
        setCreateDate(new Date());
        this.type = type;
        this.sealNo = sealNo;
    }
}
