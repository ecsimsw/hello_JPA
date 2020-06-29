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

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setName("memberA");
            memberA.setTeam(teamA);
           // teamA.getMembers().add(memberA);

//            em.flush();
//            em.clear();

            for(Member m : teamA.getMembers()){
                System.out.println(m.getName());
            }

            for(Member m : teamB.getMembers()){
                System.out.println(m.getName());
            }

            Team findTeamA = em.find(Team.class, teamA.getId());

            for(Member m : findTeamA.getMembers()){
                System.out.println("teamA : "+m.getName());
            }

            Team findTeamB = em.find(Team.class, teamB.getId());

            for(Member m : findTeamB.getMembers()){
                System.out.println("teamB : " +m);
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}
