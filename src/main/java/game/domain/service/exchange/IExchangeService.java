package game.domain.service.exchange;

import game.domain.model.exchange.Exchange;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
public interface IExchangeService {

    List<Exchange> list(String userId);
}
