package game.application.recharge;

import game.application.recharge.representation.RechargeSelectRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.recharge.IRechargeSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 16-7-11.
 */
@Service("rechargeSelectAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RechargeSelectAppService implements IRechargeSelectAppService {

    private final IRechargeSelectService rechargeSelectService;

    private final IMappingService mappingService;

    @Autowired
    public RechargeSelectAppService(IMappingService mappingService, IRechargeSelectService rechargeSelectService) {
        this.mappingService = mappingService;
        this.rechargeSelectService = rechargeSelectService;
    }

    @Override
    public List<RechargeSelectRepresentation> list() {
        return mappingService.mapAsList(rechargeSelectService.list(), RechargeSelectRepresentation.class);
    }
}
