package com.jyujyu.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestApi {

    @GetMapping("/hello/world")
    public String helloWorld() {
        return "[Get] Hello, World!";
    }

    @PostMapping("/hello/world")
    public String postHelloWorld() {
        return "[Post] Hello, World!";
    }

    @PutMapping("/hello/world")
    public String putHelloWorld() {
        return "[Put] Hello, World!";
    }

    @DeleteMapping("/hello/world")
    public String deleteHelloWorld() {
        return "[Delete] Hello, World!";
    }

}
