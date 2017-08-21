package game.application.moneydetailed.representation.mapping;

import game.application.moneydetailed.representation.MoneyDetailedRepresentation;
import game.domain.model.moneydetailed.MoneyDetailed;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class MoneyDetailedRepresentationMapper extends CustomMapper<MoneyDetailed, MoneyDetailedRepresentation> {

    public void mapAtoB(MoneyDetailed moneyDetailed, MoneyDetailedRepresentation representation, MappingContext context) {
        if (null != moneyDetailed.getUser()) {
            representation.setUserId(moneyDetailed.getUser().getUserId());
            representation.setNickname(moneyDetailed.getUser().getNickname());
        }
    }
}
