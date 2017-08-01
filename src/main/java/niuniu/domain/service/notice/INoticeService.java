package niuniu.domain.service.notice;

import niuniu.application.notice.command.CreateNoticeCommand;
import niuniu.application.notice.command.EditNoticeCommand;
import niuniu.application.notice.command.ListNoticeCommand;
import niuniu.domain.model.notice.Notice;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 16-9-6.
 */
public interface INoticeService {

    Pagination<Notice> pagination(ListNoticeCommand command);

    void create(CreateNoticeCommand notice);

    void delete(String id);

    Notice searchByID(String id);

    Notice edit(EditNoticeCommand command);

    Notice apiGet();
}
