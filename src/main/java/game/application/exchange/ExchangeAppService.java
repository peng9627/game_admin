package game.application.exchange;

import game.application.exchange.representation.ExchangeRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.exchange.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Service("exchangeAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, transactionManager = "adminTransactionManager")
public class ExchangeAppService implements IExchangeAppService {

    private final IExchangeService exchangeService;
    private final IMappingService mappingService;

    @Autowired
    public ExchangeAppService(IExchangeService exchangeService, IMappingService mappingService) {
        this.exchangeService = exchangeService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRepresentation> list(String userId) {
        return mappingService.mapAsList(exchangeService.list(userId), ExchangeRepresentation.class);
    }
}
