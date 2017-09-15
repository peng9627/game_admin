package game.application.integraldetailed.representation.mapping;

import game.application.integraldetailed.representation.IntegralDetailedRepresentation;
import game.domain.model.integraldetailed.IntegralDetailed;
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
public class IntegralDetailedRepresentationMapper extends CustomMapper<IntegralDetailed, IntegralDetailedRepresentation> {

    public void mapAtoB(IntegralDetailed integralDetailed, IntegralDetailedRepresentation representation, MappingContext context) {
        if (null != integralDetailed.getUser()) {
            representation.setUserId(integralDetailed.getUser().getUserId());
            if (null != integralDetailed.getUser().getNicknameByte()) {
                try {
                    byte[] nicknameByte = new byte[(int) integralDetailed.getUser().getNicknameByte().length()];
                    integralDetailed.getUser().getNicknameByte().getBinaryStream().read(nicknameByte);
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
