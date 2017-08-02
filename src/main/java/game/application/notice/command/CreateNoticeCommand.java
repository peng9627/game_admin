package game.application.notice.command;

/**
 * Author pengyi
 * Date 16-9-6.
 */
public class CreateNoticeCommand {

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
