package app.just.actionstorage.serivce;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.actionstorage.mapper.ActionEntityMapper;
import app.just.actionstorage.repository.ActionRepository;
import app.just.actionstorage.repository.SourceRepository;
import app.just.common.dto.ActionDto;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActionService {
    private final ActionEntityMapper actionEntityMapper;
    private final ActionRepository actionRepository;
    private final SourceRepository sourceRepository;

    public ActionService(ActionEntityMapper actionEntityMapper, ActionRepository actionRepository,
                         SourceRepository sourceRepository) {
        this.actionEntityMapper = actionEntityMapper;
        this.actionRepository = actionRepository;
        this.sourceRepository = sourceRepository;
    }

    public List<ActionDto> proceedNewActions(List<CreateNewActionRequestDto> newActions) {
        Map<String, List<ActionEntity>> actionsByUser = newActions.stream()
                .filter(action -> action != null && action.getSource() != null && action.getSource().isActive())
                .map(actionEntityMapper::toNewEntity)
                .collect(Collectors.groupingBy(ActionEntity::getUsername));

        List<ActionEntity> savedActions = new ArrayList<>();

        for (Map.Entry<String, List<ActionEntity>> entry : actionsByUser.entrySet()) {
            List<ActionEntity> actions = entry.getValue();
            if (isValidActionList(actions)) {
                List<SourceEntity> source = getSource(actions);
                sourceRepository.saveAll(source);

                savedActions.addAll(actionRepository.saveAll(actions));
            }
        }

        return savedActions.stream()
                .map(actionEntityMapper::toDto)
                .collect(Collectors.toList());

    }

    private boolean isValidActionList(List<ActionEntity> actions) {
        Set<ActionType> types = actions.stream()
                .map(ActionEntity::getType)
                .collect(Collectors.toSet());

        return types.contains(ActionType.START_TRANSACTION) && types.contains(ActionType.FINISH_TRANSACTION);
    }

    private List<SourceEntity> getSource(List<ActionEntity> entities) {
        return entities.stream()
                .map(ActionEntity::getSource)
                .distinct()
                .collect(Collectors.toList());
    }
}