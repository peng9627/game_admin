package niuniu.application.seal;

import niuniu.application.seal.command.CreateSealCommand;
import niuniu.application.seal.command.ListSealCommand;
import niuniu.domain.model.seal.Seal;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 2016/11/30.
 */
public interface ISealAppService {

    Pagination<Seal> pagination(ListSealCommand command);

    void create(CreateSealCommand command);

    void delete(String id);
}
