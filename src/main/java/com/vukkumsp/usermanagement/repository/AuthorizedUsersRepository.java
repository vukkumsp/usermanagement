package com.vukkumsp.usermanagement.repository;

import com.vukkumsp.usermanagement.entity.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizedUsersRepository extends JpaRepository<AuthorizedUser, Long> {
    public AuthorizedUser findByUsername(String username);
}
