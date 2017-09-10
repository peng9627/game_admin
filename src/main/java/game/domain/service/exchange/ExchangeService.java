package game.domain.service.exchange;

import game.domain.model.exchange.Exchange;
import game.domain.model.exchange.IExchangeRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Service("exchangeService")
public class ExchangeService implements IExchangeService {

    private final IExchangeRepository<Exchange, String> exchangeRepository;

    @Autowired
    public ExchangeService(IExchangeRepository<Exchange, String> exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public List<Exchange> list(String userId) {

        int id = Integer.valueOf(userId);
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("userId", id));
        return exchangeRepository.list(criterionList, null);
    }
}
