package game.domain.service.user;

import com.alibaba.fastjson.JSONObject;
import game.application.user.command.EditCommand;
import game.application.user.command.ListCommand;
import game.application.user.command.LoginCommand;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserService {
    Pagination<User> pagination(ListCommand command);

    User searchByUserId(int userId);

    User searchByID(String id);

    User searchByWechat(String wechat);

    void update(User user);

    void save(User user);

    User weChatLogin(LoginCommand command);

    void addGameCount(int userId);

    void share(Integer userId);

    List<User> list(String userIds);

    void updateUser(EditCommand command);

    List<User> ranking(int rankingType);

    int spreadCount(int userId);

    User loginAndBindParent(JSONObject userinfoJson);
}
