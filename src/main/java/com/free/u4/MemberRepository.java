package com.free.u4;

import com.free.u4.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }

    @Test
    public void test(){
        try{
            System.out.println("test");
            em.find(Member.class, (long) 5);
        }catch(Exception e){
            System.out.println("error");
        }
    }
}
