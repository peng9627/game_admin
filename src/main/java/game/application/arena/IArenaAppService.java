package game.application.arena;

import game.application.arena.representation.ArenaRepresentation;
import game.application.arena.representation.command.ListCommand;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
public interface IArenaAppService {

    List<ArenaRepresentation> list(ListCommand command);

    ArenaRepresentation info(String id);
}
