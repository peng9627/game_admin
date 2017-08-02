package game.application.notice;


import game.application.notice.command.CreateNoticeCommand;
import game.application.notice.command.EditNoticeCommand;
import game.application.notice.command.ListNoticeCommand;
import game.application.notice.representation.ApiNotice;
import game.core.mapping.IMappingService;
import game.domain.model.notice.Notice;
import game.domain.service.notice.INoticeService;
import game.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author pengyi
 * Date 16-9-6.
 */
@Service("noticeAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NoticeAppService implements INoticeAppService {

    private final INoticeService noticeService;
    private final IMappingService mappingService;

    @Autowired
    public NoticeAppService(INoticeService noticeService, IMappingService mappingService) {
        this.noticeService = noticeService;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<Notice> list(ListNoticeCommand command) {
        command.verifyPage();
        command.verifyPageSize(25);
        return noticeService.pagination(command);
    }

    @Override
    public void create(CreateNoticeCommand command) {
        noticeService.create(command);
    }

    @Override
    public void delete(String id) {
        noticeService.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice searchByID(String id) {
        return noticeService.searchByID(id);
    }

    @Override
    public Notice edit(EditNoticeCommand command) {
        return noticeService.edit(command);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiNotice apiGet() {
        return mappingService.map(noticeService.apiGet(), ApiNotice.class, false);
    }

}
