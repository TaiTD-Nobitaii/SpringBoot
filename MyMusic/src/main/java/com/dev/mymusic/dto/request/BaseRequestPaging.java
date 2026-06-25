package com.dev.mymusic.dto.request;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestPaging {
    private int pageSize;
    private int currentPages;
    private String search;
}
