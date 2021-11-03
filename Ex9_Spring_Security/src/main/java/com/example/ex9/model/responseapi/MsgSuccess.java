package com.example.ex9.model.responseapi;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Setter
@NoArgsConstructor
public class MsgSuccess {
    private Map<String, Object> metadata;

    private Object data;

    public MsgSuccess(int page, Long total, int limit, Object data){
        this.metadata = new HashMap<>();
        this.metadata.put("page", page);
        this.metadata.put("total", total);
        this.metadata.put("limit", limit);
        this.data = data;
    }

    public MsgSuccess(Object data){
        this.metadata = new HashMap<>();
        this.metadata.put("page", 1);
        this.metadata.put("total", 1);
        this.metadata.put("limit", 1);
        this.data = data;
    }
}
