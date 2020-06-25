package helloJPA;

import helloJPA.entitiy.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import helloJPA.entitiy.Team;
import helloJPA.entitiy.memberType;
import lombok.*;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            ////// 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.setTeamId(team.getId());

            member.setMemberType(memberType.male);
            member.setAge(24L);
            em.persist(member);

            ////// 조회 ( 데이터 중심 모델링 )

            Member saved = em.find(Member.class, member.getId());
            Long savedTeamId = saved.getTeamId();
            Team savedTeam = em.find(Team.class, savedTeamId);
            // 즉 연관관계가 없어 하나하나 다 가져와야함.
            // 객체지향에서 벗어남



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}
