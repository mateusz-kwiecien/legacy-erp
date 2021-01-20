package pl.mkwiecien.legacyerp.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT,
		classes = {ApplicationTestConfiguration.class})
class ApplicationTest {

	@Autowired
	WebApplicationContext context;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(context);
	}
}
