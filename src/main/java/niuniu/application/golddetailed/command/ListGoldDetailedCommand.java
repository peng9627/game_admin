package niuniu.application.golddetailed.command;

import niuniu.core.common.BasicPaginationCommand;
import niuniu.core.enums.FlowType;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
public class ListGoldDetailedCommand extends BasicPaginationCommand {

    private String userName;
    private FlowType flowType;
    private String startDate;
    private String endDate;
    private String description;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
