package com.free.u4.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
@Entity
public class Member {

    @Id
    private Long id;

    private String name;

//    private List<Order> orders = new ArrayList<Order>();


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


    @Override
    public String toString(){
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Member create(Member member){

        Member temp = new Member();
        temp.setId(member.getId());
        temp.setName(member.getName());

        return temp;
    }

//    @Test
//    public void test(){
//        setId((long) 50);
//        setName("조영준");
//        System.out.println(toString());
//    }
}

