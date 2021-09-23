package com.app.hifoswebserver.repository;

import com.app.hifoswebserver.domain.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTodoRepository extends JpaRepository<UserTodo, Long> {

    @Query("SELECT ut FROM UserTodo ut WHERE ut.userId = :userId")
    List<UserTodo> findAllByUserId(@Param("userId") String userId);
}
