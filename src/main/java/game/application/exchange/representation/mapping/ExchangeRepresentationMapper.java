package game.application.exchange.representation.mapping;

import game.application.exchange.representation.ExchangeRepresentation;
import game.domain.model.exchange.Exchange;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Component
public class ExchangeRepresentationMapper extends CustomMapper<Exchange, ExchangeRepresentation> {
}
