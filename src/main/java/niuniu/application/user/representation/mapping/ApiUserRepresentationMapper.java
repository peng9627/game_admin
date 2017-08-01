package niuniu.application.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import niuniu.application.user.representation.ApiUserRepresentation;
import niuniu.domain.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi on 2016/4/19.
 */
@Component
public class ApiUserRepresentationMapper extends CustomMapper<User, ApiUserRepresentation> {

    public void mapAtoB(User user, ApiUserRepresentation representation, MappingContext context) {
        if (null != user.getParent()) {
            representation.setParent(user.getParent().getUserName());
        }
    }

}
