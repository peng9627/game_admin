package game.infrastructure.persistence.hibernate.notice;

import game.domain.model.notice.INoticeRepository;
import game.domain.model.notice.Notice;
import game.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/5/6.
 */
@Repository("noticeRepository")
public class NoticeRepository extends AbstractHibernateGenericRepository<Notice, String>
        implements INoticeRepository<Notice, String> {
}
