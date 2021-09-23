package com.app.hifoswebserver.api;

import com.app.hifoswebserver.domain.UserTodo;
import com.app.hifoswebserver.dto.UserTodoDTO;
import com.app.hifoswebserver.service.UserTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class UserTodoController {
    private final UserTodoService userTodoService;

    @PostMapping(value = "/user-todo")
    public UserTodoDTO.SAVE create(@RequestBody UserTodoDTO.SAVE saveUserTodo){

        UserTodo userTodo = new UserTodo();
        userTodo.setUserId(saveUserTodo.getUserId());
        userTodo.setTodoContent(saveUserTodo.getTodoContent());

        userTodo = userTodoService.save(userTodo);
        saveUserTodo.setId(userTodo.getId());

        return saveUserTodo;
    }

    @PutMapping(value = "/user-todo/{id}")
    public UserTodoDTO.SAVE update(@PathVariable("id") Long id, @RequestBody UserTodoDTO.SAVE saveUserTodo){
        UserTodo userTodo = new UserTodo();
        userTodo.setTodoContent(saveUserTodo.getTodoContent());
        userTodo.setIsCompleted(saveUserTodo.getIsCompleted());

        userTodo = userTodoService.modify(id, userTodo);
        saveUserTodo.setId(userTodo.getId());

        return saveUserTodo;
    }

}
