package com.example.WatchNext.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequest {

    private String name;
    private Long id;

}
