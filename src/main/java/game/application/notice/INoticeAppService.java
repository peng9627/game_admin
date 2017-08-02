package game.application.notice;


import game.application.notice.command.CreateNoticeCommand;
import game.application.notice.command.EditNoticeCommand;
import game.application.notice.command.ListNoticeCommand;
import game.application.notice.representation.ApiNotice;
import game.domain.model.notice.Notice;
import game.infrastructure.persistence.hibernate.generic.Pagination;

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
