package niuniu.infrastructure.persistence.hibernate.notice;

import niuniu.domain.model.notice.INoticeRepository;
import niuniu.domain.model.notice.Notice;
import niuniu.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengyi on 2016/5/6.
 */
@Repository("noticeRepository")
public class NoticeRepository extends AbstractHibernateGenericRepository<Notice, String>
        implements INoticeRepository<Notice, String> {
}
