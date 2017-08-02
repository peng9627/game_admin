package game.application.agent;

import game.application.agent.representation.AgentRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.agent.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangjin on 2017/6/26.
 */
@Service("agentAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AgentAppService implements IAgentAppService {

    @Autowired
    private final IAgentService agentService;

    @Autowired
    private final IMappingService mappingService;

    public AgentAppService(IAgentService agentService, IMappingService mappingService) {
        this.agentService = agentService;
        this.mappingService = mappingService;
    }

    @Override
    public AgentRepresentation getAgentInfo(String userName) {

        return mappingService.map(agentService.getAgentInfo(userName), AgentRepresentation.class, false);
    }

    @Override
    public void weChatBind(String weChatNo, String userName) {
        agentService.weChatBind(weChatNo, userName);
    }
}
