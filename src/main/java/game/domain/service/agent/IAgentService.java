package game.domain.service.agent;

import game.domain.model.user.User;

/**
 * Created by zhangjin on 2017/6/26.
 */
public interface IAgentService {

    User getAgentInfo(String userName);

    void weChatBind(String weChatNo, String userName);

}