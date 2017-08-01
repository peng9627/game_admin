package niuniu.application.notice;


import niuniu.application.notice.command.CreateNoticeCommand;
import niuniu.application.notice.command.EditNoticeCommand;
import niuniu.application.notice.command.ListNoticeCommand;
import niuniu.application.notice.representation.ApiNotice;
import niuniu.domain.model.notice.Notice;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Author pengyi
 * Date 16-9-6.
 */
public interface INoticeAppService {

    Pagination<Notice> list(ListNoticeCommand command);

    void create(CreateNoticeCommand notice);

    void delete(String id);

    Notice searchByID(String id);

    Notice edit(EditNoticeCommand command);

    ApiNotice apiGet();
}
