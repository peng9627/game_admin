package game.infrastructure.persistence.hibernate.arena;

import game.domain.model.arena.Arena;
import game.domain.model.arena.IArenaRepository;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
@Repository("arenaRepository")
public class ArenaRepository extends AbstractHibernateGenericRepository<Arena, String> implements IArenaRepository<Arena, String> {
}
