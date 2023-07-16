package com.hepsi.simpletodocase.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseBaseModel<T> {

    private T data;
    private String messages;

    public ResponseBaseModel(T data) {
        this.data = data;
    }

    public ResponseBaseModel(T data, String messages) {
        this.data = data;
        this.messages = messages;
    }

}
