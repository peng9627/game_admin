package game.application.user.representation.mapping;

import game.application.user.representation.UserRepresentation;
import game.domain.model.user.User;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi on 2016/4/19.
 */
@Component
public class UserRepresentationMapper extends CustomMapper<User, UserRepresentation> {

}
