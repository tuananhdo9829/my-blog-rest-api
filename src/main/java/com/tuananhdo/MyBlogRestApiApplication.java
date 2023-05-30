package com.tuananhdo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "My Blog App REST APIs",
        description = "My Blog App REST APIs Documentation"
        ,version = "v1.0"))
public class MyBlogRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogRestApiApplication.class, args);
    }

}
