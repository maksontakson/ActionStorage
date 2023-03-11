package app.just.actionstorage.repository;

import app.just.actionstorage.entity.UserEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryIntegrationTest extends AbstractTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  public void whenFindAll_thenReturnUserEntityList() {
    List<UserEntity> all = userRepository.findAll();
    Assertions.assertEquals(3, all.size());
  }
}
