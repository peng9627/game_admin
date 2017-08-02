package game.application.logger.representation.mapping;

import game.application.logger.representation.LoggerRepresentation;
import game.domain.model.logger.Logger;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by zhangjin on 2017/6/9.
 */
@Component
public class LoggerRepresentationMapper extends CustomMapper<Logger, LoggerRepresentation> {

    public void mapAtoB(Logger logger, LoggerRepresentation representation, MappingContext context) {

        if (logger.getOperationUser() != null) {
            representation.setUserName(logger.getOperationUser().getUserName());
        }
        if (logger.getId() != null) {
            representation.setId(logger.getId());
        }
        if (logger.getCreateDate() != null) {
            representation.setCreateDate(logger.getCreateDate());
        }
    }
}
