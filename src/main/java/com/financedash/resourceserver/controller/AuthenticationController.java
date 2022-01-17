package com.financedash.resourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    public ResponseEntity<String> test(){
        //TODO: Return something from the token

        //If the token is valid, return HTTP OK
        return ResponseEntity.status(200).body("Test");
    }
}
