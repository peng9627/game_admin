package game.application.user.representation.mapping;

import game.application.user.representation.UserRepresentation;
import game.domain.model.user.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 * Created by pengyi on 2016/4/19.
 */
@Component
public class UserRepresentationMapper extends CustomMapper<User, UserRepresentation> {

    @Override
    public void mapAtoB(User user, UserRepresentation userRepresentation, MappingContext context) {
        if (null != user.getNicknameByte()) {
            try {
                byte[] nicknameByte = new byte[(int) user.getNicknameByte().length()];
                user.getNicknameByte().getBinaryStream().read(nicknameByte);
                userRepresentation.setNickname(new String(nicknameByte, Charset.forName("utf-8")));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != user.getParent()) {
            userRepresentation.setParentId(user.getParent().getUserId());
        }
    }
}
