package game.application.system.representation.mapping;

import game.application.system.representation.ApiSystemRepresentation;
import game.domain.model.system.System;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by zhangjin on 2017/6/7.
 */
@Component
public class ApiSystemRepresentationMapper extends CustomMapper<System, ApiSystemRepresentation> {
}
