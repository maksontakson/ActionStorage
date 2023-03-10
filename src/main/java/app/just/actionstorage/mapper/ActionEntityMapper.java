package app.just.actionstorage.mapper;

import app.just.actionstorage.entity.ActionEntity;
import app.just.common.dto.ActionDto;
import app.just.common.dto.CreateNewActionRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SourceEntityMapper.class})
public interface ActionEntityMapper {

    @Mapping(target = "id", expression = MappingUtils.GENERATE_UUID)
    ActionEntity toNewEntity(CreateNewActionRequestDto newAction);

    ActionDto toDto(ActionEntity actionEntity);
}
