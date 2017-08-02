package game.application.recharge.representation.mapping;

import game.application.recharge.representation.RechargeRepresentation;
import game.domain.model.recharge.Recharge;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class RechargeRepresentationMapper extends CustomMapper<Recharge, RechargeRepresentation> {

    public void mapAtoB(Recharge recharge, RechargeRepresentation rechargeRepresentation, MappingContext context) {
        if (null != recharge.getUser()) {
            rechargeRepresentation.setUser(recharge.getUser().getUserName());
        }
    }
}
