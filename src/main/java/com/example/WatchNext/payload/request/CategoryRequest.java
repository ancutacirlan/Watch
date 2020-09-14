package com.example.WatchNext.payload.request;

public class CategoryRequest {

    private String name;
    private Long id;

    public CategoryRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
