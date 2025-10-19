package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.UsuarioDetalleDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDto;
import com.javainuse.bootmysqlcrud.repository.UsuarioRepository;
import com.javainuse.bootmysqlcrud.service.EmailService;
import com.javainuse.bootmysqlcrud.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public int loginUsuario(String usuario, String clave) {
        return this.usuarioRepository.loginUsuario(usuario, clave);
    }

    public UsuarioDetalleDto obtenerDetalleUsuario(Integer idUsuario) {
        return this.usuarioRepository.obtenerDetalleUsuario(idUsuario);
    }

    public UsuarioDto obtenerUsuarioXName(String usuario) {
        return this.usuarioRepository.obtenerUsuarioXName(usuario);
    }

    public List<UsuarioDto> listar() {
        return this.usuarioRepository.listar();
    }

    public Integer registrar(UsuarioDto param) {
        return this.usuarioRepository.registrar(param);
    }

    public Integer editar(UsuarioDto param) {
        return this.usuarioRepository.editar(param);
    }

    public Integer eliminar(Integer idUsuario) {
        return this.usuarioRepository.eliminar(idUsuario);
    }

    public Integer actualizar_validar(String email, String password, String flag) {
        Integer result = Integer.valueOf(-1);
        if (flag.equals("VALIDAR")) {
            result = this.usuarioRepository.actualizar_validar(email, password, "VALIDA");
        } else if (flag.equals("ACTUALIZAR")) {
            result = this.usuarioRepository.actualizar_validar(email, password, "ACTUALIZA");
        } else if (flag.equals("RECUPERAR")) {
            Integer validar = this.usuarioRepository.actualizar_validar(email, password, "VALIDA");
            if (validar.intValue() == 1) {
                password = generarContrasena();
                String subject = "Recuperacide contraseña";
                String message = "Hola  Hemos recibido una solicitud para recuperar tu contraseTu nueva contrasees: " + password + "\n\nSi no solicitaste este cambio, ignora este mensaje.\n\nSaludos,\nEl equipo de soporte.";
                this.emailService.sendEmailHtml(email, subject, message);
                result = this.usuarioRepository.actualizar_validar(email, password, "ACTUALIZA");
                return Integer.valueOf(1);
            }
            return Integer.valueOf(999);
        }
        return result;
    }

    private String generarContrasena() {
        StringBuilder password = new StringBuilder();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        int longitud = 10;
        for (int i = 0; i < longitud; i++) {
            int index = (int)(Math.random() * caracteres.length());
            password.append(caracteres.charAt(index));
        }
        return password.toString();
    }
}
