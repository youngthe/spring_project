package com.free.u4.domain;

import javax.persistence.*;
@Entity
public class Member {

    @Id
    private Long id;

    private String name;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

//
//    @Override
//    public String toString(){
//        return "Member{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}

