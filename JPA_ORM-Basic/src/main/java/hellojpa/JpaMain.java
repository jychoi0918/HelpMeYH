package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Team team = new Team();
            team.setName("TEAM JEONGYEON");
            em.persist(team);

            Member member = new Member();
            member.setUsername("ggomi");
            member.setTeam(team);
            em.persist(member);

            team.addMember(member);
//            team.getMembers().add(member); 이미 Member Entity 내부 연관관계 편의 메소드

            em.flush();
            em.clear();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
