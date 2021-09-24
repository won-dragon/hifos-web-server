package com.app.hifoswebserver.api;

import com.app.hifoswebserver.domain.UserTodo;
import com.app.hifoswebserver.dto.UserTodoDTO;
import com.app.hifoswebserver.service.UserTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/user-todo")
    public List<UserTodoDTO.SEARCH> searchAll(){
        List<UserTodo> userTodoList = userTodoService.searchAll();
        return transferList(userTodoList);
    }

    @PutMapping(value = "/user-todo/todo/{id}")
    public UserTodoDTO.SAVE update(@PathVariable("id") Long id, @RequestBody UserTodoDTO.SAVE saveUserTodo){
        UserTodo userTodo = new UserTodo();
        userTodo.setTodoContent(saveUserTodo.getTodoContent());
        userTodo.setIsCompleted(saveUserTodo.getIsCompleted());

        userTodo = userTodoService.modify(id, userTodo);
        saveUserTodo.setId(userTodo.getId());

        return saveUserTodo;
    }

    @DeleteMapping(value = "/user-todo/todo/{id}")
    public void delete(@PathVariable("id") Long id){
        userTodoService.delete(id);
    }

    @DeleteMapping(value = "/user-todo/todo/all")
    public void deleteAll(){
        userTodoService.deleteAll();
    }

    @GetMapping(value = "/user-todo/user/{userId}")
    public List<UserTodoDTO.SEARCH> searchAllByUserId(@PathVariable("userId") String userId){
        List<UserTodo> userTodoList = userTodoService.searchAllByUserId(userId);
        return transferList(userTodoList);
    }

        private List<UserTodoDTO.SEARCH> transferList(List<UserTodo> userTodoList){
            List<UserTodoDTO.SEARCH> uerSearchList = new ArrayList<>();
            for(UserTodo ut : userTodoList){
                UserTodoDTO.SEARCH search = new UserTodoDTO.SEARCH();
                search.setId(ut.getId());
                search.setUserId(ut.getUserId());
                search.setTodoContent(ut.getTodoContent());
                search.setIsCompleted(ut.getIsCompleted());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String regDate = formatter.format(ut.getCreatedAt());
                search.setRegDate(regDate);

                uerSearchList.add(search);
            }
            return uerSearchList;
        }




}
