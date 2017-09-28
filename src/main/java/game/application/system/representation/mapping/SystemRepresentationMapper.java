package game.application.system.representation.mapping;

import game.application.system.representation.SystemRepresentation;
import game.domain.model.system.System;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class SystemRepresentationMapper extends CustomMapper<System, SystemRepresentation> {
}
