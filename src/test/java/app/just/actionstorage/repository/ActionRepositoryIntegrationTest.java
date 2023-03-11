package app.just.actionstorage.repository;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_EMAIL_SOURCE_ENTITY;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.actionstorage.entity.UserEntity;
import app.just.common.dto.ActionType;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ActionRepositoryIntegrationTest extends AbstractTest {
  @Autowired
  private ActionRepository actionRepository;
  @Autowired
  private SourceRepository sourceRepository;

  private final ActionEntity actionEntity = ActionEntity.builder()
      .id("1")
      .username("TestUsername")
      .date(Instant.now())
      .type(ActionType.CLEAN)
      .source(ACTIVE_EMAIL_SOURCE_ENTITY)
      .build();
  @Test
  public void whenFindAll_thenReturnUserEntityList() {
    sourceRepository.save(ACTIVE_EMAIL_SOURCE_ENTITY);
    List<ActionEntity> all = actionRepository.saveAll(List.of(actionEntity));
    Assertions.assertEquals(1, all.size());
  }
}
