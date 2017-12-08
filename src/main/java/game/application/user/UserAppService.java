package game.application.user;

import com.alibaba.fastjson.JSONObject;
import game.application.user.command.EditCommand;
import game.application.user.command.ListCommand;
import game.application.user.command.LoginCommand;
import game.application.user.representation.UserRepresentation;
import game.core.mapping.IMappingService;
import game.domain.model.user.User;
import game.domain.service.user.IUserService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
@Service("userAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserAppService implements IUserAppService {

    private final IUserService userService;

    private final IMappingService mappingService;

    @Autowired
    public UserAppService(IUserService userService, IMappingService mappingService) {
        this.userService = userService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<UserRepresentation> pagination(ListCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<User> pagination = userService.pagination(command);
        List<UserRepresentation> data = mappingService.mapAsList(pagination.getData(), UserRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public UserRepresentation searchByID(String id) {
        return mappingService.map(userService.searchByID(id), UserRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRepresentation info(int userId) {
        return mappingService.map(userService.searchByUserId(userId), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation weChatLogin(LoginCommand command) {
        return mappingService.map(userService.weChatLogin(command), UserRepresentation.class, false);
    }

    @Override
    public void share(Integer userId) {
        userService.share(userId);
    }

    @Override
    public List<UserRepresentation> list(String userIds) {
        return mappingService.mapAsList(userService.list(userIds), UserRepresentation.class);
    }

    @Override
    public void update(EditCommand command) {
        userService.updateUser(command);
    }

    @Override
    public List<UserRepresentation> ranking(int rankingType) {
        return mappingService.mapAsList(userService.ranking(rankingType), UserRepresentation.class);
    }

    @Override
    public int spreadCount(int userId) {
        return userService.spreadCount(userId);
    }

    @Override
    public UserRepresentation loginAndBindParent(JSONObject userinfoJson) {
        return mappingService.map(userService.loginAndBindParent(userinfoJson), UserRepresentation.class, false);
    }

}
