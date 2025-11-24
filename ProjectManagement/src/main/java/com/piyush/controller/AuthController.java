package com.piyush.controller;

import com.piyush.dto.LoginRequest;
import com.piyush.dto.RegisterRequest;
import com.piyush.model.User;
import com.piyush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User newUser = userService.register(request);

        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");
        resp.put("message", "User registered successfully");
        resp.put("user", newUser);

        return ResponseEntity.ok(resp);
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.login(request);

        Map<String, Object> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("message", "Login successful");

        return ResponseEntity.ok(resp);
    }
}
