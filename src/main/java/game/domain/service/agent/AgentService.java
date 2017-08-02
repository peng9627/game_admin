package game.domain.service.agent;

import game.core.exception.ExistException;
import game.domain.model.user.IUserRepository;
import game.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangjin on 2017/6/26.
 */
@Service("agentService")
public class AgentService implements IAgentService {

    @Autowired
    private final IUserRepository<User, String> userRepository;

    public AgentService(IUserRepository<User, String> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getAgentInfo(String userName) {

        return userRepository.searchByName(userName);
    }

    @Override
    public void weChatBind(String weChatNo, String userName) {

        User user = userRepository.searchByName(userName);
        if (user != null) {
            if (user.getWeChatNo() != null && !"".equals(user.getWeChatNo())) {
                throw new ExistException();//已绑定过微信
            }
            user.setWeChatNo(weChatNo);
            userRepository.update(user);
        }
    }

}
