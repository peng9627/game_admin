package niuniu.domain.service.system;

import niuniu.application.system.command.EditSystemCommand;
import niuniu.domain.model.system.ISystemRepository;
import niuniu.domain.model.system.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author pengyi
 * Date 16-9-5.
 */
@Service("systemService")
public class SystemService implements ISystemService {

    private final ISystemRepository<System, String> systemRepository;

    @Autowired
    public SystemService(ISystemRepository<System, String> systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public System getSystem() {
        List<System> systems = systemRepository.list(null, null);
        if (null != systems && systems.size() > 0) {
            return systems.get(0);
        }
        return null;
    }

    @Override
    public System edit(EditSystemCommand command) {
        List<System> systems = systemRepository.list(null, null);
        System system;
        if (null != systems && systems.size() > 0) {
            system = systems.get(0);
        } else {
            system = new System();
            system.setCreateDate(new Date());
        }
        system.setUserAgreement(command.getUserAgreement());
        system.setRatio(command.getRatio());
        system.setCountMultiple(command.getCountMultiple());
        system.setRegisterGive(command.getRegisterGive());
        system.setSpreadGive(command.getSpreadGive());
        system.setExtensionDomain(command.getExtensionDomain());
        system.setPayurl(command.getPayurl());
        system.setAgentGroup(command.getAgentGroup());
        system.setWeChatNumber(command.getWeChatNumber());
        system.setCustomerService(command.getCustomerService());
        systemRepository.save(system);
        return system;
    }
}
