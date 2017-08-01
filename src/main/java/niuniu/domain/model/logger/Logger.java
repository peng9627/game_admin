package niuniu.domain.model.logger;

import niuniu.core.enums.LoggerType;
import niuniu.core.id.ConcurrencySafeEntity;
import niuniu.domain.model.user.User;

import java.util.Date;

/**
 * Author pengyi
 * Date 17-4-21.
 */
public class Logger extends ConcurrencySafeEntity {

    private User operationUser;                 //操作的人
    private LoggerType loggerType;              //日志类型
    private String loggerContent;               //日志内容
    private String ip;                          //ip

    public User getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(User operationUser) {
        this.operationUser = operationUser;
    }

    public LoggerType getLoggerType() {
        return loggerType;
    }

    public void setLoggerType(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public String getLoggerContent() {
        return loggerContent;
    }

    public void setLoggerContent(String loggerContent) {
        this.loggerContent = loggerContent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Logger() {
        setCreateDate(new Date());
    }

    public Logger(User user, LoggerType loggerType, String loggerContent, String ip) {
        this();
        this.operationUser = user;
        this.loggerType = loggerType;
        this.loggerContent = loggerContent;
        this.ip = ip;
    }
}
