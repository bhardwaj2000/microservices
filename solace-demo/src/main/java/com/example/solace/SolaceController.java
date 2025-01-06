package com.example.solace;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SolaceController {

    @GetMapping
    public ResponseEntity<String> checkHealth(){
        return ResponseEntity.ok("health is good");
    }
}
