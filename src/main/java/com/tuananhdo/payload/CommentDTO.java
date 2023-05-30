package com.tuananhdo.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class CommentDTO {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 5, message = "Content should have at least 10 characters")
    private String content;
}
