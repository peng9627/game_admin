package game.application.seal.command;

import game.core.common.BasicPaginationCommand;
import game.core.enums.SealType;

/**
 * Author pengyi
 * Date 16-11-24.
 */
public class ListSealCommand extends BasicPaginationCommand {

    private SealType type;
    private String sealNo;

    public SealType getType() {
        return type;
    }

    public void setType(SealType type) {
        this.type = type;
    }

    public String getSealNo() {
        return sealNo;
    }

    public void setSealNo(String sealNo) {
        this.sealNo = sealNo;
    }
}
