package game.application.goods.representation.mapping;

import game.application.goods.representation.GoodsRepresentation;
import game.domain.model.goods.Goods;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 17-9-1.
 * desc:
 */
@Component
public class GoodsRepresentationMapper extends CustomMapper<Goods, GoodsRepresentation> {
}
