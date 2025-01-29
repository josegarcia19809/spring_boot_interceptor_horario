package com.example.interceptor_horario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {
    @GetMapping("/bienvenida")
    public ResponseEntity<?> bienvenida() {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenidos al sistema de atención");
        data.put("time", new Date());
        return ResponseEntity.ok(data);
    }
}
