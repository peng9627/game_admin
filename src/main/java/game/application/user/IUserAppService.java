package game.application.user;

import game.application.user.command.ListCommand;
import game.application.user.command.LoginCommand;
import game.application.user.representation.UserRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserAppService {

    Pagination<UserRepresentation> pagination(ListCommand command);

    UserRepresentation searchByID(String id);

    UserRepresentation info(int userId);

    UserRepresentation weChatLogin(LoginCommand command);
}
