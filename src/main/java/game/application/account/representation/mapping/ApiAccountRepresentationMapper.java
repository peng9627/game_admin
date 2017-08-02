package game.application.account.representation.mapping;

import game.application.account.representation.AccountRepresentation;
import game.domain.model.account.Account;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
@Component
public class ApiAccountRepresentationMapper extends CustomMapper<Account, AccountRepresentation> {
}
