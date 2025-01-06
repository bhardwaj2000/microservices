package com.example.wiki.consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class KafkaDatabaseController {

    // https://localhost:8083/wiki/consumer
    @GetMapping("/wiki")
    public ResponseEntity<String> checkService(){
        return ResponseEntity.ok("Service health is good at port ");
    }
}
