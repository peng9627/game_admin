package niuniu.application.recharge.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import niuniu.application.recharge.representation.ApiRechargeRepresentation;
import niuniu.domain.model.recharge.Recharge;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class ApiRechargeRepresentationMapper extends CustomMapper<Recharge, ApiRechargeRepresentation> {
}
