package com.tuananhdo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "REST APIs for Home Resource")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello Rest API2xx";
    }
}
