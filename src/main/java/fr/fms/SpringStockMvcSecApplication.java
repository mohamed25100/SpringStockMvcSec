package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {
	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//GenerateDatas();
//		articleRepository.save(new Article(null,"S8" ,"Samsung",250,null));
//		articleRepository.save(new Article(null,"S9" ,"Samsung",300,null));
//		articleRepository.save(new Article(null,"10" ,"Iphone",500,null));
//		articleRepository.save(new Article(null,"11" ,"Iphone",550,null));
//		articleRepository.save(new Article(null,"12" ,"Iphone",600,null));
//		articleRepository.save(new Article(null,"13" ,"Iphone",650,null));
//		articleRepository.save(new Article(null,"14" ,"Iphone",700,null));
//		articleRepository.save(new Article(null,"15" ,"Iphone",800,null));


		//categoryRepository.save(new Category("smartphone"));
		//categoryRepository.save(new Category("PC"));
		//categoryRepository.save(new Category("tablette"));
		//categoryRepository.save(new Category("camera"));
		//articleRepository.findAll().forEach(System.out::println);
	}
	private void GenerateDatas(){
		Category smartphone = categoryRepository.save(new Category(1,"Smartphone",null));

		Category pc = categoryRepository.save(new Category(2,"Ordinateur",null));
		Category tablet = categoryRepository.save(new Category(3, "Tablette", null));
		Category printer = categoryRepository.save(new Category(4, "Imprimante", null));
		Category camera = categoryRepository.save(new Category(5, "Camera", null));
		Category tv = categoryRepository.save(new Category(6, "TV", null));
		Category telescope = categoryRepository.save(new Category(7, "Telescope", null));
		Category gps = categoryRepository.save(new Category(8, "Gps", null));
		Category enceinte = categoryRepository.save(new Category(9, "Enceinte", null));

		articleRepository.save(new Article(1L,"Samsung","S8",250,1,smartphone));
		articleRepository.save(new Article(2L,"Samsung","S9", 300,1,smartphone));
		articleRepository.save(new Article(3L,"Iphone","10",500,1,smartphone));
		articleRepository.save(new Article(4L,"Xiaomi","MI11",100,1,smartphone));
		articleRepository.save(new Article(5L,"OnePlus","9 Pro",200,1,smartphone));
		articleRepository.save(new Article(6L,"Google","Pixel 5",350,1,smartphone));
		articleRepository.save(new Article(7L,"Poco","F3",150,1,smartphone));

		articleRepository.save(new Article(8L,"Dell","810",550,1,pc));
		articleRepository.save(new Article(9L,"Asus","F756",600,1,pc));
		articleRepository.save(new Article(10L,"Asus","E80",700,1,pc));
		articleRepository.save(new Article(11L,"MacBook","Pro",1000,1,pc));
		articleRepository.save(new Article(12L,"MacBook","Air",1200,1,pc));

		articleRepository.save(new Article(13L,"IPad","XL 5",300,1,tablet));
		articleRepository.save(new Article(14L,"IPad","XL 7",500,1,tablet));


		articleRepository.save(new Article(15L,"Canon","MG30",50,1,printer));
		articleRepository.save(new Article(16L,"Canon","MG50",60,1,printer));
		articleRepository.save(new Article(17L,"HP","800",50,1,printer));
		articleRepository.save(new Article(18L,"Epson","3T",100,1,printer));

		articleRepository.save(new Article(19L,"GoPro","7",150,1,camera));
		articleRepository.save(new Article(20L,"GoPro","10",200,1,camera));

		articleRepository.save(new Article(21L,"Panasonic","HT",1500,1,tv));
		articleRepository.save(new Article(22L,"Philips","L43",450,1,tv));
	}
}
