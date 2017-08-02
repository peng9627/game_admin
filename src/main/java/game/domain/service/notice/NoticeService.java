package game.domain.service.notice;

import game.application.notice.command.CreateNoticeCommand;
import game.application.notice.command.EditNoticeCommand;
import game.application.notice.command.ListNoticeCommand;
import game.core.exception.NoFoundException;
import game.domain.model.notice.INoticeRepository;
import game.domain.model.notice.Notice;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author pengyi
 * Date 16-9-6.
 */
@Service("noticeService")
public class NoticeService implements INoticeService {

    @Autowired
    private INoticeRepository<Notice, String> noticeRepository;

    @Override
    public Pagination<Notice> pagination(ListNoticeCommand command) {

        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));

        return noticeRepository.pagination(command.getPage(), command.getPageSize(), null, orderList);
    }

    @Override
    public void create(CreateNoticeCommand command) {
        Notice notice = new Notice(command.getTitle(), command.getContent());
        noticeRepository.save(notice);
    }

    @Override
    public void delete(String id) {
        Notice notice = searchByID(id);
        noticeRepository.delete(notice);
    }

    @Override
    public Notice searchByID(String id) {
        Notice notice = noticeRepository.getById(id);
        if (null == notice) {
            throw new NoFoundException();
        }
        return notice;
    }

    @Override
    public Notice edit(EditNoticeCommand command) {
        Notice notice = searchByID(command.getId());
        notice.fainWhenConcurrencyViolation(command.getVersion());
        notice.changeTitle(command.getTitle());
        notice.changeContent(command.getContent());
        noticeRepository.update(notice);
        return notice;
    }

    @Override
    public Notice apiGet() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        List<Notice> notices = noticeRepository.list(null, orderList);
        if (0 < notices.size()) {
            return notices.get(0);
        }
        return null;
    }
}
