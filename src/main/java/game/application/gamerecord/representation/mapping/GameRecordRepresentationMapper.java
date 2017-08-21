package game.application.gamerecord.representation.mapping;

import game.application.gamerecord.representation.GameRecordRepresentation;
import game.domain.model.gamerecord.GameRecord;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class GameRecordRepresentationMapper extends CustomMapper<GameRecord, GameRecordRepresentation> {

    public void mapAtoB(GameRecord gameRecord, GameRecordRepresentation representation, MappingContext context) {
        if (null != gameRecord.getGameData()) {
            try {
                byte[] bytes = new byte[(int) gameRecord.getGameData().length()];
                gameRecord.getGameData().getBinaryStream().read(bytes);
                representation.setData(bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
