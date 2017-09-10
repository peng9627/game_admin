package game.application.gamerecord.representation.mapping;

import game.application.gamerecord.representation.GameRecordInfoRepresentation;
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
public class GameRecordInfoRepresentationMapper extends CustomMapper<GameRecord, GameRecordInfoRepresentation> {

    public void mapAtoB(GameRecord gameRecord, GameRecordInfoRepresentation representation, MappingContext context) {
        if (null != gameRecord.getGameData()) {
            try {
                byte[] gameData = new byte[(int) gameRecord.getGameData().length()];
                gameRecord.getGameData().getBinaryStream().read(gameData);
                representation.setData(gameData);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}