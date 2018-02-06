package game.application.task;

import game.application.task.representation.TaskRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Service("taskAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskAppService implements ITaskAppService {

    private final ITaskService taskService;

    private final IMappingService mappingService;

    @Autowired
    public TaskAppService(ITaskService taskService, IMappingService mappingService) {
        this.taskService = taskService;
        this.mappingService = mappingService;
    }

    @Override
    public List<TaskRepresentation> list() {
        return mappingService.mapAsList(taskService.list(), TaskRepresentation.class);
    }
}
