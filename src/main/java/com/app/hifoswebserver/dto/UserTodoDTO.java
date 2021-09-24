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

    @Getter
    @Setter
    public static class SEARCH {
        private Long id;
        private String userId;
        private String todoContent;
        private String isCompleted;
        private String regDate;
    }
}
