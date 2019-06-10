package com.example.isp;

public class model {

    String postId , title , desc , date ;

    public model() {
    }

    public model(String postId, String title, String desc, String date) {
        this.postId = postId;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
