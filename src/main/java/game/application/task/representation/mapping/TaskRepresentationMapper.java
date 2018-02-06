package game.application.task.representation.mapping;

import game.application.task.representation.TaskRepresentation;
import game.domain.model.task.Task;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class TaskRepresentationMapper extends CustomMapper<Task, TaskRepresentation> {
}
