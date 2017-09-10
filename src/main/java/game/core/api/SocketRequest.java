package game.core.api;

import game.core.enums.NoticeType;

/**
 * Created by pengyi
 * Date : 17-9-10.
 * desc:
 */
public class SocketRequest {

    private NoticeType noticeType;
    private int userId;

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
