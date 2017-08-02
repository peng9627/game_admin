package game.application.notice.representation;

/**
 * 公告
 * Created by pengyi on 2016/4/15.
 */
public class ApiNotice {

    private String title;           //公告标题
    private String content;         //公告内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
