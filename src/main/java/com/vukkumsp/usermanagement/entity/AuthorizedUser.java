package com.vukkumsp.usermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorizedUser {

    @Id
    private Long id;

    private String username;
    private String password;

    @Column(name = "app_name")
    private String appName;
}
