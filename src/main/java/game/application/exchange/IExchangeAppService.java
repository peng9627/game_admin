package game.application.exchange;

import game.application.exchange.representation.ExchangeRepresentation;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
public interface IExchangeAppService {

    List<ExchangeRepresentation> list(String userId);
}
