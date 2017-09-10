package game.application.user;

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

}
