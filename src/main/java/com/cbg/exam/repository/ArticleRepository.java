package com.cbg.exam.repository;

import com.cbg.exam.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a join fetch a.board")
    List<Article> findAllJoinFetch();

}

//@Repository
//@RequiredArgsConstructor
//public class ArticleRepository {
//
//    private final EntityManager em;
//
//    public Long save(Article article){
//        em.persist(article);
//        return article.getId();
//    }
//
//    public Long delete(Article article){
//        em.remove(article);
//        return article.getId();
//    }
//
//    public Long modify(Article article){
//        return save(article);
//    }
//
//    public Article findById(int id){
//        return em.find(Article.class, id);
//    }
//
//    public List findAll(){
//        return em.createQuery("SELECT a FROM Article a")
//                .getResultList();
//    }
//}

