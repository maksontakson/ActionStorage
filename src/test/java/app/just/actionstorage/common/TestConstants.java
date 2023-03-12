package app.just.actionstorage.common;

import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.ActionType;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import java.time.Instant;
import java.util.UUID;

public final class TestConstants {

  private TestConstants() {
  }

  public static final class Path {

    public static final String ACTION_CONTROLLER_POST = "/actions/save";
    public static final String USER_CONTROLLER_POST = "/users";

    private Path() {
    }
  }

  public static final class Model {
    public static final String USER_NAME1 = "test_user_first";
    public static final String USER_NAME2 = "test_second_user";
    public static final ActionType VALID_ACTION_TYPE_FINISH_TRANSACTION =
        ActionType.FINISH_TRANSACTION;
    public static final ActionType VALID_ACTION_TYPE_START_TRANSACTION =
        ActionType.START_TRANSACTION;
    public static final ActionType INVALID_ACTION_TYPE = ActionType.CLEAN;
    public static final Instant DATE = Instant.now();

    private Model() {
    }

    public static final class UserEntityAttributes {

      public static final String EMAIL = "test@gmail.com";
      public static final String DESCRIPTION = "testDescription";

      private UserEntityAttributes() {
      }
    }

    public static final class CreateNewActionRequestDtoAttributes {

      public static final SourceDto ACTIVE_EMAIL_SOURCE_DTO = SourceDto.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();
      public static final SourceDto ACTIVE_PHONE_SOURCE_DTO = SourceDto.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build();

      private CreateNewActionRequestDtoAttributes() {
      }
    }

    public static final class ActionEntityAttributes {

      public static final String ID = UUID.randomUUID().toString();
      public static final SourceEntity ACTIVE_EMAIL_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();
      public static final SourceEntity ACTIVE_PHONE_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build();

      private ActionEntityAttributes() {
      }
    }
  }
}
