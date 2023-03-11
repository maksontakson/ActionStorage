package app.just.actionstorage.service;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_EMAIL_SOURCE_ENTITY;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_PHONE_SOURCE_ENTITY;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ID;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTIVE_EMAIL_SOURCE_DTO;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTIVE_PHONE_SOURCE_DTO;
import static app.just.actionstorage.common.TestConstants.Model.DATE;
import static app.just.actionstorage.common.TestConstants.Model.INVALID_ACTION_TYPE;
import static app.just.actionstorage.common.TestConstants.Model.USER_NAME1;
import static app.just.actionstorage.common.TestConstants.Model.USER_NAME2;
import static app.just.actionstorage.common.TestConstants.Model.VALID_ACTION_TYPE_FINISH_TRANSACTION;
import static app.just.actionstorage.common.TestConstants.Model.VALID_ACTION_TYPE_START_TRANSACTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.mapper.ActionEntityMapper;
import app.just.actionstorage.repository.ActionRepository;
import app.just.actionstorage.repository.SourceRepository;
import app.just.actionstorage.serivce.ActionService;
import app.just.common.dto.ActionDto;
import app.just.common.dto.CreateNewActionRequestDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActionServiceUnitTest {
  @InjectMocks
  private ActionService actionService;
  @Mock
  private ActionEntityMapper actionEntityMapper;
  @Mock
  private ActionRepository actionRepository;
  @Mock
  private SourceRepository sourceRepository;
  private static final CreateNewActionRequestDto actionRequestValidDto1 =
      CreateNewActionRequestDto.builder()
          .username(USER_NAME1)
          .date(DATE)
          .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
          .source(ACTIVE_EMAIL_SOURCE_DTO)
          .build();
  private static final CreateNewActionRequestDto actionRequestValidDto2 =
      CreateNewActionRequestDto.builder()
          .username(USER_NAME1)
          .date(DATE)
          .type(VALID_ACTION_TYPE_START_TRANSACTION)
          .source(ACTIVE_PHONE_SOURCE_DTO)
          .build();
  private static final CreateNewActionRequestDto actionRequestInvalidDto =
      CreateNewActionRequestDto.builder()
          .username(USER_NAME2)
          .date(DATE)
          .type(INVALID_ACTION_TYPE)
          .source(ACTIVE_PHONE_SOURCE_DTO)
          .build();

  private static final ActionEntity validActionEntity1 = ActionEntity.builder()
      .id(ID)
      .username(USER_NAME1)
      .date(DATE)
      .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
      .source(ACTIVE_EMAIL_SOURCE_ENTITY)
      .build();
  private static final ActionEntity validActionEntity2 = ActionEntity.builder()
      .id(ID)
      .username(USER_NAME1)
      .date(DATE)
      .type(VALID_ACTION_TYPE_START_TRANSACTION)
      .source(ACTIVE_PHONE_SOURCE_ENTITY)
      .build();
  private static final ActionEntity invalidActionEntity2 = ActionEntity.builder()
      .id(ID)
      .username(USER_NAME2)
      .date(DATE)
      .type(INVALID_ACTION_TYPE)
      .source(ACTIVE_PHONE_SOURCE_ENTITY)
      .build();

  @Test
  void shouldReturnActionDtoListWhenActionRequestDtoListHasElementsWithTheSameUsernameAndValidTypesOf() {
    //when
    when(actionEntityMapper.toNewEntity(actionRequestValidDto1)).thenReturn(validActionEntity1);
    when(actionEntityMapper.toNewEntity(actionRequestValidDto2)).thenReturn(validActionEntity2);
    when(actionRepository.saveAll(any())).thenReturn(
        List.of(validActionEntity1, validActionEntity2));
    //execute
    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(actionRequestValidDto1, actionRequestValidDto2));
    //then
    Mockito.verify(actionEntityMapper, Mockito.times(2)).toNewEntity(any());
    verify(sourceRepository, only()).saveAll(any());
    verify(actionRepository, only()).saveAll(any());
    verify(actionEntityMapper, times(2)).toDto(any());
    Assertions.assertEquals(2, actionDtoList.size());
  }

  @Test
  void shouldReturnEmptyActionDtoListWhenRequestDtoListHasElementsWithDifferentUsernameAndInvalidTypeOfAction() {
    //when
    when(actionEntityMapper.toNewEntity(actionRequestValidDto1)).thenReturn(validActionEntity1);
    when(actionEntityMapper.toNewEntity(actionRequestInvalidDto)).thenReturn(invalidActionEntity2);
    //execute
    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(actionRequestValidDto1, actionRequestInvalidDto));
    //then
    Mockito.verify(actionEntityMapper, Mockito.times(2)).toNewEntity(any());
    verify(sourceRepository, never()).saveAll(any());
    verify(actionRepository, never()).saveAll(any());
    verify(actionEntityMapper, never()).toDto(any());
    Assertions.assertEquals(0, actionDtoList.size());
  }

  @Test
  void shouldReturnEmptyActionDtoListWhenActionRequestDtoListHasOneElement() {
    //when
    when(actionEntityMapper.toNewEntity(actionRequestValidDto1)).thenReturn(validActionEntity1);
    //execute
    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(actionRequestValidDto1));
    //then
    Mockito.verify(actionEntityMapper, Mockito.times(1)).toNewEntity(any());
    verify(sourceRepository, never()).saveAll(any());
    verify(actionRepository, never()).saveAll(any());
    verify(actionEntityMapper, never()).toDto(any());
    Assertions.assertEquals(0, actionDtoList.size());
  }
}
