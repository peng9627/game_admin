package game.application.seal;

import game.application.seal.command.CreateSealCommand;
import game.application.seal.command.ListSealCommand;
import game.domain.model.seal.Seal;
import game.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 2016/11/30.
 */
public interface ISealAppService {

    Pagination<Seal> pagination(ListSealCommand command);

    void create(CreateSealCommand command);

    void delete(String id);
}
