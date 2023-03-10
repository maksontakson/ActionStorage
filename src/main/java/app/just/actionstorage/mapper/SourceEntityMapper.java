package app.just.actionstorage.mapper;

import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.SourceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceEntityMapper {

    SourceEntity toEntity(SourceDto dto);

    SourceDto toDto(SourceEntity entity);
}
