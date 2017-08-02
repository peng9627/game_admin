package game.application.agent.representation.mapping;

import game.application.agent.representation.AgentRepresentation;
import game.domain.model.user.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by zhangjin on 2017/6/26.
 */
@Component
public class AgentRepresentationMapper extends CustomMapper<User, AgentRepresentation> {

    public void mapAtoB(User user, AgentRepresentation representation, MappingContext context) {

        if (user.getParent() == null) {
            representation.setAgentLevel(1);
        } else {
            representation.setAgentLevel(2);
        }

        if (user.getParent() != null && user.getParent().getInviteCode() != null) {
            representation.setHigherInviteCode(user.getParent().getInviteCode());
        }
        if (user.getMoney() != null) {
            representation.setIncome(user.getMoney());
        }
        if (user.getLastLoginDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            representation.setLastLoginDate(format.format(user.getLastLoginDate()));
        }
    }
}
