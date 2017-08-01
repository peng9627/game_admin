package niuniu.application.account.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import niuniu.application.account.representation.AccountRepresentation;
import niuniu.domain.model.account.Account;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
@Component
public class ApiAccountRepresentationMapper extends CustomMapper<Account, AccountRepresentation> {
}
