package com.free.u4.domain;

import javax.persistence.Entity;


@Entity
public class User {

    private String id;
    private String pw;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

}
