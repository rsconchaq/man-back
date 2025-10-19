package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.config.GenerateJWT;
import com.javainuse.bootmysqlcrud.dto.TokenDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDetalleDto;
import com.javainuse.bootmysqlcrud.entity.JwtRequest;
import com.javainuse.bootmysqlcrud.service.TokenService;
import com.javainuse.bootmysqlcrud.service.UsuarioService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/authentication"})
public class AuthenticationController {
    @Autowired
    private GenerateJWT generateJwt;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping({"/login"})
    public ResponseEntity<ResponseWrapper> authentication(@RequestBody JwtRequest userDto) {
        Integer idUser = Integer.valueOf(this.usuarioService.loginUsuario(userDto.getUsername(), userDto.getPassword()));
        if (idUser == null || idUser.intValue() == 0)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(Integer.valueOf(0), "Usuario o contraseincorrectos"));
        UsuarioDetalleDto usuario = this.usuarioService.obtenerDetalleUsuario(idUser);
        if (usuario == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(null, "Usuario no encontrado"));
        TokenDto tokenDto = this.generateJwt.generateToken(usuario);
        if (tokenDto == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper(null, "Error al generar el token"));
        Integer idToken = this.tokenService.registrarToken(idUser, "REST", usuario.getLoginUsuario());
        if (idToken == null || idToken.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper(null, "Error al registrar el token"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(tokenDto, "Token Generado"));
    }

    @GetMapping({"/validartoken"})
    public ResponseEntity<ResponseWrapper> validate(@RequestParam String token, @RequestParam Integer idUsuario) {
        Integer res = this.tokenService.validarToken(idUsuario, token);
        if (res.intValue() == 1)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(res, "Token OPT válido"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(res, "Token OPT no vo expirado"));
    }

    @GetMapping  ({"/validaciones"})
    public ResponseEntity<ResponseWrapper> validate(@RequestParam String email, @RequestParam String flag) {
        Integer res = this.usuarioService.actualizar_validar(email, "", flag);
        if (res.intValue() == 999)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Correo electrno no válido", "Correo electrno no válido"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(res, "Validaciexitosa"));
    }
}
