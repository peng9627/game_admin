package niuniu.infrastructure.persistence.hibernate.logger;

import niuniu.domain.model.logger.ILoggerRepository;
import niuniu.domain.model.logger.Logger;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Author pengyi
 * Date 17-4-21.
 */
@Repository("loggerRepository")
public class LoggerRepository extends AbstractHibernateGenericRepository<Logger, String>
        implements ILoggerRepository<Logger, String> {
}
