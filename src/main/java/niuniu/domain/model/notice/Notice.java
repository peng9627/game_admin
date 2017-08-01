package niuniu.domain.model.notice;

import niuniu.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * 公告
 * Created by pengyi on 2016/4/15.
 */
public class Notice extends ConcurrencySafeEntity {

    private String title;           //公告标题
    private String content;         //公告内容

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Notice() {
        super();
    }

    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
        this.setCreateDate(new Date());
    }
}
