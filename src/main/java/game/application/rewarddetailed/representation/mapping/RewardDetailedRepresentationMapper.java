package game.application.rewarddetailed.representation.mapping;

import game.application.rewarddetailed.representation.RewardDetailedRepresentation;
import game.domain.model.rewarddetailed.RewardDetailed;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class RewardDetailedRepresentationMapper extends CustomMapper<RewardDetailed, RewardDetailedRepresentation> {

    public void mapAtoB(RewardDetailed rewardDetailed, RewardDetailedRepresentation representation, MappingContext context) {
        if (null != rewardDetailed.getUser()) {
            representation.setUserId(rewardDetailed.getUser().getUserId());
            if (null != rewardDetailed.getUser().getNicknameByte()) {
                try {
                    byte[] nicknameByte = new byte[(int) rewardDetailed.getUser().getNicknameByte().length()];
                    rewardDetailed.getUser().getNicknameByte().getBinaryStream().read(nicknameByte);
                    representation.setNickname(new String(nicknameByte, Charset.forName("utf-8")));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
