package jpabook_jpashop;

import jpabook_jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistance.xml에 설정된 name 속성이 "hello"
        EntityManager em = emf.createEntityManager(); // 클라이언트의 요청이 올 때마다 작업을 하려면 EntityManager를 생성해야 함

        EntityTransaction tx = em.getTransaction(); // transaction 가져오기
        tx.begin(); // transaction 시작

        try {
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);

            tx.commit(); // 에러가 발생하지 않았을 땐 commit (DB에 쿼리를 날림)
        } catch (Exception e) {
            tx.rollback(); // 에러가 발생하면 rollback
        } finally {
            em.close(); // EntityManager는 항상 닫아줘야 함.
        }
        emf.close();
    }
}
