package com.app.hifoswebserver.dto;

import lombok.Getter;
import lombok.Setter;

public class UserTodoDTO {

    @Getter
    @Setter
    public static class SAVE {
        private Long id;
        private String userId;
        private String todoContent;
        private String isCompleted;
    }
}
