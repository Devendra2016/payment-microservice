import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.company.payment.infrastructure.persistence.entity")
@EnableJpaRepositories("com.company.payment.infrastructure.persistence.repository")
public class TestConfig {
}
