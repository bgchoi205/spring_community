package com.cbg.exam.repository;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Article article){
        em.persist(article);
    }

    @Transactional
    public Article findById(int id){
        return em.find(Article.class, id);
    }

    @Transactional
    public List findAll(){
        return em.createQuery("SELECT a FROM Article a")
                .getResultList();
    }
}

//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//}
