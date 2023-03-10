package app.just.actionstorage.serivce;

import app.just.actionstorage.mapper.UserEntityMapper;
import app.just.actionstorage.repository.UserRepository;
import app.just.common.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserService(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userEntityMapper::toDto)
                .collect(Collectors.toList());
    }
}
