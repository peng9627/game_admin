package game.application.moneydetailed;

import game.application.moneydetailed.command.CreateCommand;
import game.application.moneydetailed.command.ListCommand;
import game.application.moneydetailed.representation.MoneyDetailedRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.moneydetailed.MoneyDetailed;
import game.domain.service.moneydetailed.IMoneyDetailedService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
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
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, transactionManager = "transactionManager")
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
    public Pagination<MoneyDetailedRepresentation> pagination(ListCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<MoneyDetailed> pagination = moneyDetailedService.pagination(command);
        List<MoneyDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), MoneyDetailedRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public void create(CreateCommand command) {
        moneyDetailedService.create(command);
    }
}
