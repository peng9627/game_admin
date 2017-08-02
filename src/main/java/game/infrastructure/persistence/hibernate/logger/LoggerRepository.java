package game.infrastructure.persistence.hibernate.logger;

import game.domain.model.logger.ILoggerRepository;
import game.domain.model.logger.Logger;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Author pengyi
 * Date 17-4-21.
 */
@Repository("loggerRepository")
public class LoggerRepository extends AbstractHibernateGenericRepository<Logger, String>
        implements ILoggerRepository<Logger, String> {
}
