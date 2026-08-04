package io.github.henrique0120.libraryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        return authentication;
    }

}
