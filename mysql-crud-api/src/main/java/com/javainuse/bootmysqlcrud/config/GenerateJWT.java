package com.javainuse.bootmysqlcrud.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.dto.TokenDto;
import com.javainuse.bootmysqlcrud.dto.UserTokenDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDetalleDto;
import com.javainuse.bootmysqlcrud.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenerateJWT {
    private final UsuarioService usuarioService;

    private String secretKey = "yourBase64EncodedSecretKeyyourBase64EncodedSecretKeyyourBase64EncodedSecretKey";

    private  long expirationTimeMillis = 43200000L;

    public GenerateJWT(UsuarioService usuarioService) {
        this.secretKey = "yourBase64EncodedSecretKeyyourBase64EncodedSecretKeyyourBase64EncodedSecretKey";
        this.expirationTimeMillis = 43200000L;  //43200000L
        this.usuarioService = usuarioService;
    }

    public TokenDto generateToken(UsuarioDetalleDto user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 43200000L);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getLoginUsuario());
        String token = Jwts.builder().setId("schollAcademy").claim("user", user).claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).setIssuedAt(now).setExpiration(expiration).signWith(SignatureAlgorithm.HS512, "yourBase64EncodedSecretKeyyourBase64EncodedSecretKeyyourBase64EncodedSecretKey".getBytes()).compact();
        TokenDto tokenDto = new TokenDto();
        tokenDto.setExpiration(expiration);
        tokenDto.setNow(now);
        tokenDto.setToken("Bearer " + token);
        UserTokenDto userDto = new UserTokenDto();
        userDto.setId(user.getIdUsuario());
        userDto.setUsername(user.getLoginUsuario());
        userDto.setFirstName(user.getNombres());
        userDto.setLastName(user.getApellidos());
        userDto.setEmail(user.getLoginUsuario());
        userDto.setToken(token);
        tokenDto.setCurrentUser(toJson(userDto));
        return tokenDto;
    }

    private String toJson(Object obj) {
        try {
            return (new ObjectMapper()).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al serializar el objeto a JSON", e);
        }
    }
}
