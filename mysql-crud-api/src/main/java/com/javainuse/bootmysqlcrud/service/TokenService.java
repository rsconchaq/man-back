package com.javainuse.bootmysqlcrud.service;

public interface TokenService {
    Integer validarToken(Integer paramInteger, String paramString);

    Integer registrarToken(Integer paramInteger, String paramString1, String paramString2);
}
