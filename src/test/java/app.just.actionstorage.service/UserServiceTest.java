package app.just.actionstorage.service;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.just.actionstorage.entity.UserEntity;
import app.just.actionstorage.mapper.UserEntityMapper;
import app.just.actionstorage.repository.UserRepository;
import app.just.actionstorage.serivce.UserService;
import app.just.common.dto.UserDto;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserEntityMapper userEntityMapper;
  private static final UserEntity userEntity = UserEntity.builder()
      .username("testUsername")
      .email("test@gmail.com")
      .description("testDescription")
      .build();

  @Test
  void shouldFindAllUsersThenMapThemToDtoAndReturnListOfDto() {
    when(userRepository.findAll()).thenReturn(List.of(userEntity));
    List<UserDto> all = userService.findAll();
    verify(userRepository, only()).findAll();
    verify(userEntityMapper, atLeastOnce()).toDto(userEntity);
    Assertions.assertEquals(1, all.size());
  }

  @Test
  void shouldThrowNullPointerExceptionWhenDbReturnNull() {
    when(userRepository.findAll()).thenReturn(null);
    Assertions.assertThrows(NullPointerException.class, () -> userService.findAll());
  }
}
