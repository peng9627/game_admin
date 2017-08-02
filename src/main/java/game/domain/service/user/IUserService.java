package game.domain.service.user;

import game.application.auth.command.LoginCommand;
import game.application.shared.command.SharedCommand;
import game.application.user.command.*;
import game.domain.model.user.User;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.LockMode;

import java.math.BigDecimal;

/**
 * Created by pengyi
 * Date : 2016/4/19.
 */
public interface IUserService {
    Pagination<User> pagination(ListUserCommand command);

    User searchByName(String userName);

    boolean checkDeviceNo(String deviceNo);

    User searchByName(String userName, LockMode lockMode);

    User searchByID(String id);

    User create(CreateUserCommand command);

    User edit(EditUserCommand command);

    void update(User user);

    void save(User user);

    void addMoney(MoneyCommand command);

    void subtractMoney(MoneyCommand command);

    void updateVip(SharedCommand command);

    void updateRanking();

    BigDecimal totalMoney(ListUserCommand command);

    void spreadGet(String id);

    User login(LoginCommand command);

    User weChatLogin(LoginCommand command);

    void bindInviteCode(InviteCodeCommand command);

    String searchIdByToken(String token);

    BigDecimal receiveGold(String userName);

    BigDecimal receiveBenefit(String userName);

}
