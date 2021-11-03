package com.example.ex9.model.responseapi;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@NoArgsConstructor
public class MsgError {
    private String error;
    private List<String> messages;
    private int code;

    public MsgError(String error, List<String> messages, int code) {
        this.error = error;
        this.messages = messages;
        this.code = code;
    }
}
