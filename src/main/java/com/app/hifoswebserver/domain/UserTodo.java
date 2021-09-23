package com.app.hifoswebserver.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "USER_TODO")
public class UserTodo extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", columnDefinition = "varchar(20)", nullable = false)
    private String userId;

    @Column(name = "TODO_CONTENT", columnDefinition = "varchar(50)", nullable = false)
    private String todoContent;

    @Column(name="IS_COMPLETED", columnDefinition = "varchar(1)")
    private String isCompleted = "N";

}
