package com.vukkumsp.usermanagement.service;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.repository.AuthorizedUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizedUserService {

    private final AuthorizedUsersRepository userRepo;

    public AuthorizedUserService(AuthorizedUsersRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<AuthorizedUser> getAllUsers(){
        return this.userRepo.findAll();
    }
}
