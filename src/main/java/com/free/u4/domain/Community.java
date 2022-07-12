package com.free.u4.domain;

import javax.persistence.Entity;

@Entity
public class Community {

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

