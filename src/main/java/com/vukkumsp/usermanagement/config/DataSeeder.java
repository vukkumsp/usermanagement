package com.vukkumsp.usermanagement.config;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.repository.AuthorizedUsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    private final AuthorizedUsersRepository userRepo;
    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(AuthorizedUsersRepository userRepo, JdbcTemplate jdbcTemplate){
        this.userRepo = userRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void seedData(){
//        jdbcTemplate.execute("DROP TABLE IF EXISTS authorized_user");
        // 1. Create table if not exists
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS authorized_user (
                id BIGINT PRIMARY KEY,
                username VARCHAR(255),
                password VARCHAR(255)
            )
        """);
        // 2. Seed data
        if(userRepo.count()==0){
            userRepo.save(new AuthorizedUser(1L,"admin","admin"));
            userRepo.save(new AuthorizedUser(2L,"test","test"));
            userRepo.save(new AuthorizedUser(3L,"temp","temp"));
            System.out.println("Records added");
        }
        else{
            System.out.println("Already has records");
        }
    }
}
