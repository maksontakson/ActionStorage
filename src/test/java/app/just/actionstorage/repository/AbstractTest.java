package app.just.actionstorage.repository;

import java.nio.channels.ClosedChannelException;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractTest {
  private static final PostgreSQLContainer CONTAINER = (PostgreSQLContainer) new PostgreSQLContainer<>("postgres:11.7")
      .withReuse(true);

  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", CONTAINER::getUsername);
    registry.add("spring.datasource.password", CONTAINER::getPassword);
  }

  @BeforeAll
  public static void setup() {
      CONTAINER.start();
  }
}
