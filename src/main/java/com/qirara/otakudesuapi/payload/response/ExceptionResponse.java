package com.qirara.otakudesuapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse<T> {

    private Integer code;

    private String status;

    private T data;
}
