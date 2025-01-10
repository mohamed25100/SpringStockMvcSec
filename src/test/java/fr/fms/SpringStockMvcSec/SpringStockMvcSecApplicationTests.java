package fr.fms.SpringStockMvcSec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Article;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

@SpringBootTest
class SpringStockMvcSecApplicationTests {

	@Autowired
	IBusinessImpl bussiness;

	private static Instant startedAt;

	@BeforeEach
	public void beforeEachTest() {
		System.out.println("avant chaque test");
	}

	@AfterEach
	public void afterEachTest() {
		System.out.println("après chaque test");
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Apple avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Apple après tous les tests");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = {1,2,42,1011,5089})
	public void multiply_shouldReturnZero(int arg) {
		assertEquals(0,arg*0);
	}

	@Timeout(1)
	@Test
	public void orderShouldComputeLess1Second() {
		bussiness.order();
	}

	@Test
	void testTotalAmontCart() {
		// Arrage
		bussiness.addArtToCart(new Article((long)1,"Samsung","Samsung S8", 250, 1,null));
		bussiness.addArtToCart(new Article((long)2,"Samsung","Samsung S9", 250, 1,null));
		bussiness.addArtToCart(new Article((long)3,"IPhone","IPhone 10", 500, 1,null));

		//Act
		double amount = bussiness.getTotalAmount();

		//Assert
		assertEquals(amount,1000);
	}
	/*
	//slide 7
	@Test
	void contextLoads() {
		assertFalse(1==2);
	}

	@Test
	void testTotalAmontCart() {
		bussiness.addArtToCart(new Article((long)1,"Samsung","Samsung S8", 250, 1,null));
		bussiness.addArtToCart(new Article((long)2,"Samsung","Samsung S9", 250, 1,null));
		bussiness.addArtToCart(new Article((long)3,"IPhone","IPhone 10", 500, 1,null));
		bussiness.addArtToCart(new Article((long)4,"Samsung","Samsung S8", 250, 1,null));

		assertEquals(bussiness.getTotalAmount(),1250);
	}fin slide 7*/

}
