package game.domain.service.task;

import game.domain.model.task.ITaskRepository;
import game.domain.model.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Service("taskService")
public class TaskService implements ITaskService {

    private final ITaskRepository<Task, String> taskRepository;

    @Autowired
    public TaskService(ITaskRepository<Task, String> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> list() {
        return taskRepository.list(null, null);
    }
}
