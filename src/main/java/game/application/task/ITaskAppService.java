package game.application.task;

import game.application.task.representation.TaskRepresentation;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-25.
 * desc:
 */
public interface ITaskAppService {

    List<TaskRepresentation> list();
}
