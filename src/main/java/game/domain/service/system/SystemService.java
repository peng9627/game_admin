package game.domain.service.system;

import game.domain.model.system.ISystemRepository;
import game.domain.model.system.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Service("systemService")
public class SystemService implements ISystemService {

    private final ISystemRepository<System, String> systemRepository;

    @Autowired
    public SystemService(ISystemRepository<System, String> systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public System info() {
        List<System> systemList = systemRepository.list(null, null);
        return null != systemList && 0 < systemList.size() ? systemList.get(0) : new System();
    }
}
