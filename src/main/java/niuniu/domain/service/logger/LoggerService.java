package niuniu.domain.service.logger;

import niuniu.application.logger.command.CreateLoggerCommand;
import niuniu.application.logger.command.ListLoggerCommand;
import niuniu.core.util.CoreDateUtils;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.logger.ILoggerRepository;
import niuniu.domain.model.logger.Logger;
import niuniu.domain.model.user.User;
import niuniu.domain.service.user.IUserService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author pengyi
 * Date 17-4-21.
 */
@Service("loggerService")
public class LoggerService implements ILoggerService {

    private final ILoggerRepository<Logger, String> loggerRepository;
    private final IUserService userService;

    @Autowired
    public LoggerService(ILoggerRepository<Logger, String> loggerRepository, IUserService userService) {
        this.loggerRepository = loggerRepository;
        this.userService = userService;
    }

    @Override
    public void create(CreateLoggerCommand command) {
        User user = userService.searchByID(command.getUserId());
        Logger logger = new Logger(user, command.getLoggerType(), command.getLoggerContent(), command.getIp());
        loggerRepository.save(logger);
    }

    @Override
    public Pagination<Logger> pagination(ListLoggerCommand command) {

        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();

        if (!CoreStringUtils.isEmpty(command.getOperationUser())) {
            criterionList.add(Restrictions.like("operationUser.userName", command.getOperationUser(), MatchMode.ANYWHERE));
            aliasMap.put("operationUser", "operationUser");
        }
        if (!CoreStringUtils.isEmpty(command.getStartDate()) && null != CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDate(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")));
        }
        if (null != command.getLoggerType()) {
            criterionList.add(Restrictions.eq("loggerType", command.getLoggerType()));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return loggerRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }
}
