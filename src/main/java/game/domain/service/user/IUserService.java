package game.domain.service.user;

import game.application.user.command.ListUserCommand;
import game.application.user.command.LoginCommand;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserService {
    Pagination<User> pagination(ListUserCommand command);

    User searchByUserId(int userId);

    User searchByID(String id);

    User searchByWechat(String wechat);

    void update(User user);

    void save(User user);

    User weChatLogin(LoginCommand command);
}
