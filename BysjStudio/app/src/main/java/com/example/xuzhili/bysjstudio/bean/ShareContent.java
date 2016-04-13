package com.example.xuzhili.bysjstudio.bean;

/**
 * Created by xuzhili on 16/2/29.
 */
public class ShareContent {

    private String title;
    private String text;
    private String image_url;
    private String url;

    public ShareContent(String title, String text, String image_url, String url) {
        this.title = title;
        this.text = text;
        this.image_url = image_url;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}