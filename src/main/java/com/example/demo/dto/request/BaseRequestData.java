package com.example.demo.dto.request;

import lombok.Data;

/**
 *
 * @author Chidq
 */
@Data
public class BaseRequestData<T extends IRequestData> {
    private T request;
}
