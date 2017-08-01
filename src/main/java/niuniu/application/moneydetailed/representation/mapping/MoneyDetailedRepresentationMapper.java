package niuniu.application.moneydetailed.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import niuniu.application.moneydetailed.representation.MoneyDetailedRepresentation;
import niuniu.application.user.representation.UserRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.moneydetailed.MoneyDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class MoneyDetailedRepresentationMapper extends CustomMapper<MoneyDetailed, MoneyDetailedRepresentation> {

    private final IMappingService mappingService;

    @Autowired
    public MoneyDetailedRepresentationMapper(IMappingService mappingService) {
        this.mappingService = mappingService;
    }

    public void mapAtoB(MoneyDetailed moneyDetailed, MoneyDetailedRepresentation representation, MappingContext context) {
        if (null != moneyDetailed.getUser()) {
            UserRepresentation data = mappingService.map(moneyDetailed.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
    }
}
