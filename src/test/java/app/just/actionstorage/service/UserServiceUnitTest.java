package app.just.actionstorage.service;

import static app.just.actionstorage.common.TestConstants.Model.USER_NAME1;
import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.DESCRIPTION;
import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.EMAIL;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.just.actionstorage.entity.UserEntity;
import app.just.actionstorage.mapper.UserEntityMapper;
import app.just.actionstorage.repository.UserRepository;
import app.just.actionstorage.serivce.UserService;
import app.just.common.dto.UserDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
  private static final UserEntity USER_ENTITY = UserEntity.builder()
      .username(USER_NAME1)
      .email(EMAIL)
      .description(DESCRIPTION)
      .build();
  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserEntityMapper userEntityMapper;

  @Test
  void shouldFindAllUsersThenMapThemToDtoAndReturnListOfDto() {
    //when
    when(userRepository.findAll()).thenReturn(List.of(USER_ENTITY));
    //execute
    List<UserDto> all = userService.findAll();
    //then
    verify(userRepository, only()).findAll();
    verify(userEntityMapper, atLeastOnce()).toDto(USER_ENTITY);
    Assertions.assertEquals(1, all.size());
  }

  @Test
  void shouldThrowNullPointerExceptionWhenDbReturnNull() {
    //when
    when(userRepository.findAll()).thenReturn(null);
    //thenThrows
    Assertions.assertThrows(NullPointerException.class, () -> userService.findAll());
  }
}
