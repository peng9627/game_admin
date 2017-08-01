package niuniu.domain.service.seal;

import niuniu.application.seal.command.CreateSealCommand;
import niuniu.application.seal.command.ListSealCommand;
import niuniu.domain.model.seal.Seal;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 2016/11/30.
 */
public interface ISealService {

    Seal bySealNo(String seatNo);

    Pagination<Seal> pagination(ListSealCommand command);

    void create(CreateSealCommand command);

    void delete(String id);
}
