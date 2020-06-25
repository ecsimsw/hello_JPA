package helloJPA;

import helloJPA.entitiy.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import helloJPA.entitiy.memberType;
import lombok.*;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();// jpa의 모든 활동은 트랜젝션 안에서!
        tx.begin(); // 트랜젝션 시작

        try{
            Member member = new Member();
            member.setId(1L);
            member.setName("memberA");
            member.setMemberType(memberType.male);
            member.setAge(51L);
            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }

}
