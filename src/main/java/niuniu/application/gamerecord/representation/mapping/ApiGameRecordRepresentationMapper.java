package niuniu.application.gamerecord.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import niuniu.application.gamerecord.representation.ApiGameRecordRepresentation;
import niuniu.domain.model.gamerecord.GameRecord;
import org.springframework.stereotype.Component;

/**
 * Created by zhangjin on 2017/6/3.
 */
@Component
public class ApiGameRecordRepresentationMapper extends CustomMapper<GameRecord, ApiGameRecordRepresentation> {

    public void mapAtoB(GameRecord gameRecord, ApiGameRecordRepresentation representation, MappingContext context) {

        if (representation.getId() == null) {
            representation.setId(gameRecord.getId());
        }
        if (representation.getCreateDate() == null) {
            representation.setCreateDate(gameRecord.getCreateDate());
        }
    }
}
