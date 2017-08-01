package niuniu.application.recharge.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import niuniu.application.recharge.representation.RechargeRepresentation;
import niuniu.domain.model.recharge.Recharge;
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
