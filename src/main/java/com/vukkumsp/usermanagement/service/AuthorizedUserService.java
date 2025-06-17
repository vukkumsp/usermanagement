package com.vukkumsp.usermanagement.service;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.model.User;
import com.vukkumsp.usermanagement.repository.AuthorizedUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorizedUserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthorizedUsersRepository userRepo;

    public AuthorizedUserService(AuthorizedUsersRepository userRepo){
        this.userRepo = userRepo;
    }

    public Long count(){
        return userRepo.count();
    }

    public void saveUser(AuthorizedUser user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println(user.getUsername()+" : " +hashedPassword);
        user.setPassword(hashedPassword);
        userRepo.save(user);
    }

    public AuthorizedUser getUser(String username){
        return this.userRepo.findByUsername(username);
    }
}
