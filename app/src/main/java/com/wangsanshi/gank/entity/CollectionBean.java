package com.wangsanshi.gank.entity;


public class CollectionBean {
    private String id;
    private String type;
    private String date;
    private String title;
    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
