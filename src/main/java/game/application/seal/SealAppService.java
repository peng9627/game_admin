package game.application.seal;

import game.application.seal.command.CreateSealCommand;
import game.application.seal.command.ListSealCommand;
import game.domain.model.seal.Seal;
import game.domain.service.seal.ISealService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author pengyi
 * Date 2016/11/30.
 */
@Service("sealAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SealAppService implements ISealAppService {

    private final ISealService sealService;

    @Autowired
    public SealAppService(ISealService sealService) {
        this.sealService = sealService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<Seal> pagination(ListSealCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        return sealService.pagination(command);
    }

    @Override
    public void create(CreateSealCommand command) {
        sealService.create(command);
    }

    @Override
    public void delete(String id) {
        sealService.delete(id);
    }
}
