package com.cahier.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong 🏓 - API en marche !");
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
                "status", "OK",
                "timestamp", LocalDateTime.now(),
                "message", "L'API fonctionne correctement 🚀"
        ));
    }

    @PostMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> requestData) {
        return ResponseEntity.ok(Map.of(
                "received", requestData,
                "timestamp", LocalDateTime.now(),
                "message", "Données reçues avec succès !"
        ));
    }
}
