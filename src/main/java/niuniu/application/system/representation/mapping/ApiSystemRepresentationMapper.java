package niuniu.application.system.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import niuniu.application.system.representation.ApiSystemRepresentation;
import niuniu.domain.model.system.System;
import org.springframework.stereotype.Component;

/**
 * Created by zhangjin on 2017/6/7.
 */
@Component
public class ApiSystemRepresentationMapper extends CustomMapper<System, ApiSystemRepresentation> {
}
