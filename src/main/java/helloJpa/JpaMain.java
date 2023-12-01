package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //Transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Member member = new Member();
//
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
            List<Member> result = em.createQuery("select m from Member as M",Member.class)
                    .getResultList();

            for (Member member : result){
                System.out.println("member.name = " + member.getName());
            }
            tx.commit();

            /*
            // delete
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);

            // update
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJpa");

            //em.persist(findMember); 를 할 필요가 없다.
            //트랜잭션 커밋하는 시점에서 변경이 되는지 안되는지 알아서 체크한다.

             */
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
