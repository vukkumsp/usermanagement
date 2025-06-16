package com.vukkumsp.usermanagement.service;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.repository.AuthorizedUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorizedUserService {

    private final AuthorizedUsersRepository userRepo;

    public AuthorizedUserService(AuthorizedUsersRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<AuthorizedUser> getAllUsers(){
        log.info("getAllUsers called");
        return this.userRepo.findAll();
    }

    public AuthorizedUser getUser(String username){
        return this.userRepo.findByUsername(username);
    }
}
