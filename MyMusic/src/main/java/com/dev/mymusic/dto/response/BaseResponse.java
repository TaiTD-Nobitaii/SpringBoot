package com.dev.mymusic.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;
}
