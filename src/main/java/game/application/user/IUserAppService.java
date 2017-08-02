package game.application.user;

import game.application.auth.command.LoginCommand;
import game.application.shared.command.SharedCommand;
import game.application.user.command.*;
import game.application.user.representation.ApiUserRepresentation;
import game.application.user.representation.UserRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserAppService {

    Pagination<UserRepresentation> pagination(ListUserCommand command);

    UserRepresentation searchByID(String id);

    UserRepresentation create(CreateUserCommand command);

    UserRepresentation edit(EditUserCommand command);

    void addMoney(MoneyCommand command);

    void subtractMoney(MoneyCommand command);

    void updateVip(SharedCommand command);

    void register(CreateUserCommand command);

    void updateRanking();

    BigDecimal totalMoney(ListUserCommand command);

    ApiUserRepresentation login(LoginCommand command);

    ApiUserRepresentation weChatLogin(LoginCommand command);

    ApiUserRepresentation info(String username);

    void bindInviteCode(InviteCodeCommand command);

    String searchIdByToken(String token);

    BigDecimal receiveGold(String userName);

    BigDecimal receiveBenefit(String userName);
}
