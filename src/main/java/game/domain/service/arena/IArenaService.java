package game.domain.service.arena;

import game.application.arena.representation.command.ListCommand;
import game.domain.model.arena.Arena;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
public interface IArenaService {

    List<Arena> list(ListCommand command);

    Arena getById(String id);
}
