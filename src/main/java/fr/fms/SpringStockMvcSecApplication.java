package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {
	@Autowired
	ArticleRepository articleRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//articleRepository.save(new Article(null,"S8" ,"Samsung",250));
		//articleRepository.save(new Article(null,"S9" ,"Samsung",300));
		//articleRepository.save(new Article(null,"10" ,"Iphone",500));
		//articleRepository.save(new Article(null,"11" ,"Iphone",550));
		//articleRepository.save(new Article(null,"12" ,"Iphone",600));
		//articleRepository.save(new Article(null,"13" ,"Iphone",650));
		//articleRepository.save(new Article(null,"14" ,"Iphone",700));
		//articleRepository.save(new Article(null,"15" ,"Iphone",800));
		articleRepository.save(new Article(null,"16" ,"Iphone",900,new Category("smartphone")));
		articleRepository.findAll().forEach(System.out::println);
	}
}
