package game.application.moneydetailed.representation.mapping;

import game.application.moneydetailed.representation.MoneyDetailedRepresentation;
import game.domain.model.moneydetailed.MoneyDetailed;
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
public class MoneyDetailedRepresentationMapper extends CustomMapper<MoneyDetailed, MoneyDetailedRepresentation> {

    public void mapAtoB(MoneyDetailed moneyDetailed, MoneyDetailedRepresentation representation, MappingContext context) {
        if (null != moneyDetailed.getUser()) {
            representation.setUserId(moneyDetailed.getUser().getUserId());
            if (null != moneyDetailed.getUser().getNicknameByte()) {
                try {
                    byte[] nicknameByte = new byte[(int) moneyDetailed.getUser().getNicknameByte().length()];
                    moneyDetailed.getUser().getNicknameByte().getBinaryStream().read(nicknameByte);
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
