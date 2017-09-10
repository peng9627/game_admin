package game.domain.model.notice.command;

import game.core.enums.NoticeType;

/**
 * Created by pengyi
 * Date : 17-9-7.
 * desc:
 */
public class NoticeCommand {

    private NoticeType noticeType;

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }
}
