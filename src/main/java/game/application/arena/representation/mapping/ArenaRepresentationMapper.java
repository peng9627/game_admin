package game.application.arena.representation.mapping;

import game.application.arena.representation.ArenaRepresentation;
import game.domain.model.arena.Arena;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
@Component
public class ArenaRepresentationMapper extends CustomMapper<Arena, ArenaRepresentation> {
}