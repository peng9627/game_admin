package game.application.gamerecord.representation.mapping;

import game.application.gamerecord.representation.GameRecordRepresentation;
import game.domain.model.gamerecord.GameRecord;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by zhangjin on 2017/6/3.
 */
@Component
public class GameRecordRepresentationMapper extends CustomMapper<GameRecord, GameRecordRepresentation> {

    public void mapAtoB(GameRecord gameRecord, GameRecordRepresentation representation, MappingContext context) {

        if (representation.getId() == null) {
            representation.setId(gameRecord.getId());
        }
        if (representation.getCreateDate() == null) {
            representation.setCreateDate(gameRecord.getCreateDate());
        }
    }
}
