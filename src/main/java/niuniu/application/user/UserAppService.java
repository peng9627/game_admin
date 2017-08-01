package niuniu.application.user;

import niuniu.application.auth.command.LoginCommand;
import niuniu.application.shared.command.SharedCommand;
import niuniu.application.user.command.*;
import niuniu.application.user.representation.ApiUserRepresentation;
import niuniu.application.user.representation.UserRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.user.User;
import niuniu.domain.service.user.IUserService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public Pagination<UserRepresentation> pagination(ListUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        Pagination<User> pagination = userService.pagination(command);
        List<UserRepresentation> data = mappingService.mapAsList(pagination.getData(), UserRepresentation.class);
        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public UserRepresentation searchByID(String id) {
        return mappingService.map(userService.searchByID(id), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation create(CreateUserCommand command) {
        return mappingService.map(userService.create(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation edit(EditUserCommand command) {
        return mappingService.map(userService.edit(command), UserRepresentation.class, false);
    }

    @Override
    public void addMoney(MoneyCommand command) {
        userService.addMoney(command);
    }

    @Override
    public void subtractMoney(MoneyCommand command) {
        userService.subtractMoney(command);
    }

    @Override
    public void updateVip(SharedCommand command) {
        userService.updateVip(command);
    }

    @Override
    public void register(CreateUserCommand command) {
        userService.create(command);
    }

    @Override
    public void updateRanking() {
        userService.updateRanking();
    }

    @Override
    public BigDecimal totalMoney(ListUserCommand command) {
        return userService.totalMoney(command);
    }

    @Override
    public ApiUserRepresentation login(LoginCommand command) {
        return mappingService.map(userService.login(command), ApiUserRepresentation.class, false);
    }

    @Override
    public ApiUserRepresentation weChatLogin(LoginCommand command) {
        return mappingService.map(userService.weChatLogin(command), ApiUserRepresentation.class, false);
    }

    @Override
    public ApiUserRepresentation info(String username) {
        return mappingService.map(userService.searchByName(username), ApiUserRepresentation.class, false);
    }

    @Override
    public void bindInviteCode(InviteCodeCommand command) {
        userService.bindInviteCode(command);
    }

    @Override
    public String searchIdByToken(String token) {

        return userService.searchIdByToken(token);
    }

    @Override
    public BigDecimal receiveGold(String userName) {
        return userService.receiveGold(userName);
    }

    @Override
    public BigDecimal receiveBenefit(String userName) {
        return userService.receiveBenefit(userName);
    }

}
