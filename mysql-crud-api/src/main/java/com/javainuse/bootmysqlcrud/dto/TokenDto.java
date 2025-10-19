package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TokenDto {
    private String token;

    private String currentUser;

    private Date now;

    private Date expiration;
}
