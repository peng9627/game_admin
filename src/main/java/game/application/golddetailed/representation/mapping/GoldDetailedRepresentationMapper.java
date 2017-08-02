package game.application.golddetailed.representation.mapping;

import game.application.golddetailed.representation.GoldDetailedRepresentation;
import game.application.user.representation.UserRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.golddetailed.GoldDetailed;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class GoldDetailedRepresentationMapper extends CustomMapper<GoldDetailed, GoldDetailedRepresentation> {

    private final IMappingService mappingService;

    @Autowired
    public GoldDetailedRepresentationMapper(IMappingService mappingService) {
        this.mappingService = mappingService;
    }

    public void mapAtoB(GoldDetailed moneyDetailed, GoldDetailedRepresentation representation, MappingContext context) {
        if (null != moneyDetailed.getUser()) {
            UserRepresentation data = mappingService.map(moneyDetailed.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
    }
}
