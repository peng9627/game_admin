package niuniu.application.notice.command;

/**
 * Author pengyi
 * Date 16-9-6.
 */
public class EditNoticeCommand {

    private String id;
    private Integer version;
    private String title;           //公告标题
    private String content;         //公告内容

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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
