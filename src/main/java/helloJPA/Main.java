package helloJPA;

import helloJPA.entitiy.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import helloJPA.entitiy.Team;
import helloJPA.entitiy.memberType;
import lombok.*;

import java.util.List;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Member member_A = new Member();
            member_A.setName("memberA");
            member_A.setTeam(teamA);
            em.persist(member_A);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member_A.getId());
            Team findTeam= findMember.getTeam();
            System.out.println(findTeam.getName());


            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member_B = new Member();
            member_B.setName("memberB");
            member_B.setTeam(teamB);
            em.persist(member_B);

            em.flush(); // find ok

            findMember = em.find(Member.class, member_B.getId());
            findTeam= findMember.getTeam();
            System.out.println(findTeam.getName());

            Member member_C = new Member();
            member_C.setName("memberB");
            member_C.setTeam(teamB);
            em.persist(member_C);

            member_C.setTeam(teamA);
            // changing value after persist 

            em.flush();

            findMember = em.find(Member.class, member_C.getId());
            findTeam= findMember.getTeam();
            System.out.println(findTeam.getName());

            tx.commit();
        }catch (Exception e){
            System.out.println(e);
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}
