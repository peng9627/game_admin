package game.infrastructure.persistence.hibernate.task;

import game.domain.model.task.ITaskRepository;
import game.domain.model.task.Task;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
@Repository("taskRepository")
public class TaskRepository extends AbstractHibernateGenericRepository<Task, String>
        implements ITaskRepository<Task, String> {
}
