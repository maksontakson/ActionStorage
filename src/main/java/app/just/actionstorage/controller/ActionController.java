package app.just.actionstorage.controller;

import app.just.actionstorage.serivce.ActionService;
import app.just.common.dto.ActionDto;
import app.just.common.dto.CreateNewActionRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actions")
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/save")
    public List<ActionDto> save(@RequestBody List<CreateNewActionRequestDto> newActions) {
        return actionService.proceedNewActions(newActions);
    }
}
