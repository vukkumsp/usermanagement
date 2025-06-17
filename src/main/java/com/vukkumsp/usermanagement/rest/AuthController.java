package com.vukkumsp.usermanagement.rest;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.model.User;
import com.vukkumsp.usermanagement.service.AuthorizedUserService;
import com.vukkumsp.usermanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final AuthorizedUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthController(JwtService jwt, AuthorizedUserService userService){
        this.jwt = jwt;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user){
        HashMap<String, String> response = new HashMap<>();
        AuthorizedUser fetchedUser = this.userService.getUser(user.getUsername());

        if(fetchedUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), fetchedUser.getPassword());

        if(passwordMatches && user.getAppName().equals(fetchedUser.getAppName())){
            String token = jwt.generateToken(user.getUsername(), null, user.getAppName());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
