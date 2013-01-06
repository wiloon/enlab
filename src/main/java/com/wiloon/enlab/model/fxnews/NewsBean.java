package com.wiloon.enlab.model.fxnews;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/12/12
 * Time: 9:39 PM
 */
public class NewsBean {
    private String url;
    private String title;
    private String content;
    private String timeStamp;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.content = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
