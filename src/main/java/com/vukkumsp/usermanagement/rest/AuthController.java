package com.vukkumsp.usermanagement.rest;

import com.vukkumsp.usermanagement.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwt;

    public AuthController(JwtService jwt){
        this.jwt = jwt;
    }

    @GetMapping("/test")
    public String test(){
        return "test success";
    }

    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getAuthToken(){
        HashMap<String, String> response = new HashMap<>();
        response.put("token", jwt.generateToken("guest", null));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyToken(@RequestBody AuthRequest authRequest){
        HashMap<String, String> response = new HashMap<>();
        Jwt token = jwt.validateToken(authRequest.getToken());
        response.put("username",token.getSubject());
        response.put("token",token.toString());

        return ResponseEntity.ok(response);
    }
}
