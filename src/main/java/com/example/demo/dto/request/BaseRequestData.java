package com.example.demo.dto.request;

import lombok.Data;

/**
 *
 * @author Chidq
 */
@Data
public class BaseRequestData<T extends IRequestData> {
    
    private String wsCode;
    private String sessionId;
    private String token;
    private T wsRequest;
}
