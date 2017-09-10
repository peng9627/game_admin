package game.application.arena;

import game.application.arena.representation.ArenaRepresentation;
import game.application.arena.representation.command.ListCommand;
import game.core.mapping.IMappingService;
import game.domain.service.arena.IArenaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
@Service("arenaAppService")
public class ArenaAppService implements IArenaAppService {

    private final IArenaService arenaService;
    private final IMappingService mappingService;

    public ArenaAppService(IArenaService arenaService, IMappingService mappingService) {
        this.arenaService = arenaService;
        this.mappingService = mappingService;
    }

    @Override
    public List<ArenaRepresentation> list(ListCommand command) {
        return mappingService.mapAsList(arenaService.list(command), ArenaRepresentation.class);
    }

    @Override
    public ArenaRepresentation info(String id) {
        return mappingService.map(arenaService.getById(id), ArenaRepresentation.class, false);
    }
}
