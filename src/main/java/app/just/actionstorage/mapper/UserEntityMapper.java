package app.just.actionstorage.mapper;

import app.just.actionstorage.entity.UserEntity;
import app.just.common.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserDto toDto(UserEntity entity);
}
