package com.vukkumsp.usermanagement.config;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import com.vukkumsp.usermanagement.repository.AuthorizedUsersRepository;
import com.vukkumsp.usermanagement.service.AuthorizedUserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataSeeder {

    private final AuthorizedUserService userService;
    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(AuthorizedUserService userService, JdbcTemplate jdbcTemplate){
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void seedData(){
        log.info("seeding data into database");
        jdbcTemplate.execute("DROP TABLE IF EXISTS authorized_user");
        // 1. Create table if not exists
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS authorized_user (
                id BIGINT PRIMARY KEY,
                username VARCHAR(255),
                password VARCHAR(255),
                app_name VARCHAR(255)
            )
        """);
        log.info("created authorized_user table if it doesn't exist");
        // 2. Seed data
        if(userService.count()==0){
            userService.saveUser(new AuthorizedUser(1L,"admin","admin", "notes-service"));
            userService.saveUser(new AuthorizedUser(2L,"test","test", "notes-service"));
            userService.saveUser(new AuthorizedUser(3L,"temp","temp", "notes-service"));
            log.info("added test data into authorized_user if it doesn't have any data");
        }
        else{
            log.info("authorized_user table already has data");
        }
    }
}
