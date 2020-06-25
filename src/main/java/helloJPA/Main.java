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
            ////// 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member_A = new Member();
            member_A.setName("memberA");
            member_A.setTeam(team);
            em.persist(member_A);

            Member member_B = new Member();
            member_B.setName("memberB");
            member_B.setTeam(team);
            em.persist(member_B);

            Member member_C = new Member();
            member_C.setName("memberC");
            member_C.setTeam(team);

            em.persist(member_C);

            //team.getMembers().add(member_A);  종속은 읽기만 가능하다.!
            //team.getMembers().add(member_B);  member의 team에 영향x
            //team.getMembers().add(member_C);  TEAM_ID : null로 들어감

            em.flush(); // db에 쿼리를 다 보냄
            em.clear(); // 캐시 비움
            //바로 find시 team에 members가 적용이 안되어 있음

            ////// member to team 조회 ( 단방향 매핑 )

            Member findMember = em.find(Member.class, member_A.getId());
            Team findTeam_= findMember.getTeam();

            /////// team to members 조회 (양방향 매핑)

            List<Member> members = findTeam_.getMembers();
            for(Member m : members){
                System.out.println(m.getName());
            }

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
