package com.dev.mymusic.dto.response;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class BaseResponsePaging<T> {
    List<T> data;
    private int total;
}
