package pl.mkwiecien.legacyerp.application;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ComponentScan(basePackages = {"pl.mkwiecien.legacyerp"})
//@EnableJpaRepositories(value = {"pl.mkwiecien.legacyerp.domain"})
//@EntityScan(value = {"pl.mkwiecien.legacyerp.domain"})
//@ActiveProfiles(value = {"test"})
public class ApplicationTestConfiguration {
}
