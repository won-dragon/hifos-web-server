package com.app.hifoswebserver.service;

import com.app.hifoswebserver.domain.UserTodo;
import com.app.hifoswebserver.repository.UserTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTodoService {
    private final UserTodoRepository userTodoRepository;

    public UserTodo save(UserTodo userTodo) {
        userTodo = userTodoRepository.save(userTodo);
        return userTodo;
    }

    public UserTodo modify(Long id, UserTodo userTodo) {
        Optional<UserTodo> findUserTodo = userTodoRepository.findById(id);
        if(findUserTodo.isPresent()){
            UserTodo getUserTodo = findUserTodo.get();
            getUserTodo.setIsCompleted(userTodo.getIsCompleted());
            getUserTodo.setTodoContent(userTodo.getTodoContent());
            getUserTodo = userTodoRepository.save(getUserTodo);
            return getUserTodo;
        }else{
            UserTodo temp = new UserTodo();
            temp.setId(-1L);
            return temp;
        }
    }

    public List<UserTodo> searchAllByUserId(String userId){
        return userTodoRepository.findAllByUserId(userId);
    }

    public List<UserTodo> searchAll(){
        return userTodoRepository.findAll();
    }

    public void delete(Long id){
        userTodoRepository.deleteById(id);
    }

    public void deleteAll(){
        userTodoRepository.deleteAll();
    }
}
