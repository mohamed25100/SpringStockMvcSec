package fr.fms.SpringStockMvcSec;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SpringShopJpaTests {/*
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void test_add_article() {
        //GIVEN
        Category anonymous = categoryRepository.save(new Category(null,"anonymous",null));
        articleRepository.save(new Article(null,"incognito","incognito 007" , 375 , 1 , anonymous));

        //WHEN
        Article article = articleRepository.findByBrandContains("incognito").get(0);

        //THEN
        assertEquals("incognito 007", article.getDescription());
    }

    @Test
    void should_find_one_article() {
        Iterable<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotEmpty();
    }*/
}
