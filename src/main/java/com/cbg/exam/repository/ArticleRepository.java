package com.cbg.exam.repository;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public Long save(Article article){
        em.persist(article);
        return article.getId();
    }

    public Long delete(Article article){
        em.remove(article);
        return article.getId();
    }

    public Long modify(Article article){
        return save(article);
    }

    public Article findById(int id){
        return em.find(Article.class, id);
    }

    public List findAll(){
        return em.createQuery("SELECT a FROM Article a")
                .getResultList();
    }
}

//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//}
