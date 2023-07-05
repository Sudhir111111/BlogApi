package com.blogapi.paylod;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message =" title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 4, message =" description should have at least 4 characters")
    private  String description;
    @NotEmpty

    private String content;

}
