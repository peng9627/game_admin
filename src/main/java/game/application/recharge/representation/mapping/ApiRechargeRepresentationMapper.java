package game.application.recharge.representation.mapping;

import game.application.recharge.representation.ApiRechargeRepresentation;
import game.domain.model.recharge.Recharge;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class ApiRechargeRepresentationMapper extends CustomMapper<Recharge, ApiRechargeRepresentation> {
}
