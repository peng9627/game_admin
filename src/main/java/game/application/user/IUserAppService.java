package game.application.user;

import com.alibaba.fastjson.JSONObject;
import game.application.user.command.EditCommand;
import game.application.user.command.ListCommand;
import game.application.user.command.LoginCommand;
import game.application.user.representation.UserRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserAppService {

    Pagination<UserRepresentation> pagination(ListCommand command);

    UserRepresentation searchByID(String id);

    UserRepresentation info(int userId);

    UserRepresentation weChatLogin(LoginCommand command);

    void share(Integer userId);

    List<UserRepresentation> list(String userIds);

    void update(EditCommand command);

    List<UserRepresentation> ranking(int rankingType);

    int spreadCount(int userId);

    UserRepresentation loginAndBindParent(JSONObject userinfoJson);

    void addCount(int userId, boolean create);
}
