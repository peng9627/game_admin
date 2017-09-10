package game.application.goods;

import game.application.goods.representation.GoodsRepresentation;

import java.util.List;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
public interface IGoodsAppService {

    List<GoodsRepresentation> list();
}
