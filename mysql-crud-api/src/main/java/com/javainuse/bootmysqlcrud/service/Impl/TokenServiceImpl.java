package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.repository.TokenRepository;
import com.javainuse.bootmysqlcrud.service.EmailService;
import com.javainuse.bootmysqlcrud.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    public Integer validarToken(Integer idUsuario, String token) {
        Integer resultado = this.tokenRepository.validarToken(idUsuario, token);
        if (resultado != null)
            return resultado;
        throw new RuntimeException("Codigo de seguridad no vo no encontrado para el usuario con ID: " + idUsuario);
    }

    public Integer registrarToken(Integer idUsuario, String type, String loginUsuario) {
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaExpiracionLocal = fechaActual.plusMinutes(15L);
        Date fechaExpiracion = Timestamp.valueOf(fechaExpiracionLocal);
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder tokenBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++)
            tokenBuilder.append(caracteres.charAt(random.nextInt(caracteres.length())));
        String token = tokenBuilder.toString();
        String to = loginUsuario;
        String subject = "Codigo de seguridad para la aplicación";
        String html = "<h1>Codigo de seguridad</h1><p>Tu codigo es: <b>" + token + "</b></p><p>Este Codigo de seguridad expiraren 15 minutos.</p>";
        this.emailService.sendEmailHtml(to, subject, html);
        Integer resultado = this.tokenRepository.registrarToken(idUsuario, token, fechaExpiracion, type);
        if (resultado != null)
            return resultado;
        throw new RuntimeException("Error al registrar el codigo de seguridad para el usuario con ID: " + idUsuario);
    }
}