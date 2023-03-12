package app.just.actionstorage.repository;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_EMAIL_SOURCE_ENTITY;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ID;
import static app.just.actionstorage.common.TestConstants.Model.DATE;
import static app.just.actionstorage.common.TestConstants.Model.USER_NAME1;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.entity.ActionEntity;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.spring.api.DBRider;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ActionRepositoryIntegrationTest extends AbstractTest {
  @Autowired
  private ActionRepository actionRepository;

  private final ActionEntity actionEntity = ActionEntity.builder()
      .id(ID)
      .username(USER_NAME1)
      .date(DATE)
      .type(TestConstants.Model.VALID_ACTION_TYPE_START_TRANSACTION)
      .source(ACTIVE_EMAIL_SOURCE_ENTITY)
      .build();

  @Test
  public void whenFindAll_thenReturnUserEntityList() {
    List<ActionEntity> all = actionRepository.saveAll(List.of(actionEntity));
    Assertions.assertEquals(1, all.size());
  }
}
