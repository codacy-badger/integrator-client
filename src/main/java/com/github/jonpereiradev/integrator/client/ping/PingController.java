package com.github.jonpereiradev.integrator.client.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/status")
public class PingController {

    @GetMapping
    public ResponseEntity<Void> get() {
        return ResponseEntity.ok().build();
    }

}
