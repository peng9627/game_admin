package niuniu.application.moneydetailed;

import niuniu.application.moneydetailed.command.CreateMoneyDetailedCommand;
import niuniu.application.moneydetailed.command.ListMoneyDetailedCommand;
import niuniu.application.moneydetailed.representation.MoneyDetailedRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.moneydetailed.MoneyDetailed;
import niuniu.domain.service.moneydetailed.IMoneyDetailedService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
@Service("moneyDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MoneyDetailedAppService implements IMoneyDetailedAppService {

    private final IMoneyDetailedService moneyDetailedService;

    private final IMappingService mappingService;

    @Autowired
    public MoneyDetailedAppService(IMoneyDetailedService moneyDetailedService, IMappingService mappingService) {
        this.moneyDetailedService = moneyDetailedService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<MoneyDetailed> pagination = moneyDetailedService.pagination(command);
        List<MoneyDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), MoneyDetailedRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public boolean receiveTask(ListMoneyDetailedCommand command) {
        return moneyDetailedService.receiveTask(command);
    }

    @Override
    public void create(CreateMoneyDetailedCommand command) {
        moneyDetailedService.create(command);
    }
}
