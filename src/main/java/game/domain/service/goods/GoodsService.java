package game.domain.service.goods;

import game.domain.model.goods.Goods;
import game.domain.model.goods.IGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Service("goodsService")
public class GoodsService implements IGoodsService {

    private final IGoodsRepository<Goods, String> goodsRepository;

    @Autowired
    public GoodsService(IGoodsRepository<Goods, String> goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> list() {
        return goodsRepository.list(null, null);
    }
}
