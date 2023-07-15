package com.hepsi.simpletodocase.dto.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ItemTodoDTO {

    private String description;

    @Nullable
    private Boolean isDone;

}
