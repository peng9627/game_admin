package game.domain.service.notice;

import game.application.notice.command.CreateNoticeCommand;
import game.application.notice.command.EditNoticeCommand;
import game.application.notice.command.ListNoticeCommand;
import game.domain.model.notice.Notice;
import game.infrastructure.persistence.hibernate.generic.Pagination;

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
