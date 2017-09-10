package game.domain.service.arena;

import game.application.arena.representation.command.ListCommand;
import game.core.exception.NoFoundException;
import game.domain.model.arena.Arena;
import game.domain.model.arena.IArenaRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyi
 * Date : 17-8-28.
 * desc:
 */
@Service("arenaService")
public class ArenaService implements IArenaService {

    private final IArenaRepository<Arena, String> arenaRepository;

    public ArenaService(IArenaRepository<Arena, String> arenaRepository) {
        this.arenaRepository = arenaRepository;
    }

    @Override
    public List<Arena> list(ListCommand command) {
        List<Criterion> criterionList = new ArrayList<>();

        if (null != command.getGameType()) {
            criterionList.add(Restrictions.eq("gameType", command.getGameType()));
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return arenaRepository.list(criterionList, orderList, null, null, null);
    }

    @Override
    public Arena getById(String id) {
        Arena arena = arenaRepository.getById(id);
        if (null == arena) {
            throw new NoFoundException();
        }
        return arena;
    }
}
