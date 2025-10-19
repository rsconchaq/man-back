package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTokenDto {
    private Integer id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String token;
}
