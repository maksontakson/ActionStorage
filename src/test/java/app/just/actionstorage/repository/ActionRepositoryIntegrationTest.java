package app.just.actionstorage.repository;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_EMAIL_SOURCE_ENTITY;

import app.just.actionstorage.entity.ActionEntity;
import app.just.common.dto.ActionType;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DBRider
public class ActionRepositoryIntegrationTest extends AbstractTest {
  @Autowired
  private ActionRepository actionRepository;

  private final ActionEntity actionEntity = ActionEntity.builder()
      .id("1")
      .username("TestUsername")
      .date(Instant.now())
      .type(ActionType.CLEAN)
      .source(ACTIVE_EMAIL_SOURCE_ENTITY)
      .build();
  @Test
  @DataSet(value = "source.yml")
  @DBUnit(caseSensitiveTableNames = true)
  public void whenFindAll_thenReturnUserEntityList() {
    List<ActionEntity> all = actionRepository.saveAll(List.of(actionEntity));
    Assertions.assertEquals(1, all.size());
  }
}
