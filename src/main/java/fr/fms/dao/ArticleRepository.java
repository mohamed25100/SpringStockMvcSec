package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Page<Article> findByDescriptionContains(String description, Pageable pageable);
    //Page<Article> findByCategoryId(Long categoryId, Pageable pageable);
    List<Article> findByCategoryId(Long categoryId);
    public Page<Article> findByCategoryId(Long categoryId , Pageable pageable);
}
