package game.application.goods;

import game.application.goods.representation.GoodsRepresentation;
import game.core.mapping.IMappingService;
import game.domain.service.goods.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Service("goodsAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, transactionManager = "adminTransactionManager")
public class GoodsAppService implements IGoodsAppService {

    private final IGoodsService goodsService;
    private final IMappingService mappingService;

    @Autowired
    public GoodsAppService(IGoodsService goodsService, IMappingService mappingService) {
        this.goodsService = goodsService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoodsRepresentation> list() {
        return mappingService.mapAsList(goodsService.list(), GoodsRepresentation.class);
    }
}
