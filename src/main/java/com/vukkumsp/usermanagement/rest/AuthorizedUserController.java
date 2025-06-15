package com.vukkumsp.usermanagement.rest;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.service.AuthorizedUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizedUserController {

    private final AuthorizedUserService userService;

    public AuthorizedUserController(AuthorizedUserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "test success";
    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthorizedUser>> getUsers(){
        List<AuthorizedUser> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
