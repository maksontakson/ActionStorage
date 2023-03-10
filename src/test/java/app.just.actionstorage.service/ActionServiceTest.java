package app.just.actionstorage.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.actionstorage.mapper.ActionEntityMapper;
import app.just.actionstorage.repository.ActionRepository;
import app.just.actionstorage.repository.SourceRepository;
import app.just.actionstorage.serivce.ActionService;
import app.just.common.dto.ActionDto;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActionServiceTest {
  @InjectMocks
  private ActionService actionService;
  @Mock
  private  ActionEntityMapper actionEntityMapper;
  @Mock
  private ActionRepository actionRepository;
  @Mock
  private SourceRepository sourceRepository;
  private static final CreateNewActionRequestDto requestDto1 = CreateNewActionRequestDto.builder()
      .username("testUsername1")
      .date(Instant.now())
      .type(ActionType.CLEAN)
      .source(SourceDto.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build())
      .build();
  private static final CreateNewActionRequestDto requestDto2 = CreateNewActionRequestDto.builder()
      .username("testUsername2")
      .date(Instant.now())
      .type(ActionType.START_TRANSACTION)
      .source(SourceDto.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build())
      .build();

  private static final ActionEntity actionEntity1 = ActionEntity.builder()
      .id(UUID.randomUUID().toString())
      .username("testUsername")
      .date(Instant.now())
      .type(ActionType.FINISH_TRANSACTION)
      .source(SourceEntity.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build())
      .build();
  private static final ActionEntity actionEntity2 = ActionEntity.builder()
      .id(UUID.randomUUID().toString())
      .username("testUsername")
      .date(Instant.now())
      .type(ActionType.START_TRANSACTION)
      .source(SourceEntity.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build())
      .build();

  @Test
  void proceedNewActions() {
    when(actionEntityMapper.toNewEntity(requestDto1)).thenReturn(actionEntity1);
    when(actionEntityMapper.toNewEntity(requestDto2)).thenReturn(actionEntity2);
    List<ActionDto> actionDtos = actionService.proceedNewActions(List.of(requestDto1, requestDto2));
    Mockito.verify(actionEntityMapper, Mockito.times(2)).toNewEntity(any());
    verify(sourceRepository, only()).saveAll(any());
    verify(actionRepository, only()).saveAll(any());
  }
}
