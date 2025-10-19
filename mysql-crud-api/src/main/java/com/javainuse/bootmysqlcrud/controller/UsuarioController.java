package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.UsuarioDto;
import com.javainuse.bootmysqlcrud.service.UsuarioService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/usuario"})
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<UsuarioDto> listaUsuario = this.usuarioService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaUsuario, "Lista de Usuarioes"));
    }

    @GetMapping({"/obtener/{idUsuario}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idUsuario) {
        List<UsuarioDto> listaUsuario = this.usuarioService.listar();
        UsuarioDto Usuario = listaUsuario.stream().filter(r -> r.getIdUsuario().equals(idUsuario)).findFirst().orElse(null);
        if (Usuario == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Usuario no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(Usuario, "Lista Usuario"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody UsuarioDto param) {
        Integer idUsuario = this.usuarioService.registrar(param);
        if (idUsuario == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el Usuario"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idUsuario, "Usuario registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody UsuarioDto param) {
        Integer idUsuario = this.usuarioService.editar(param);
        if (idUsuario == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el Usuario"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idUsuario, "Usuario editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idUsuario}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idUsuario) {
        Integer result = this.usuarioService.eliminar(idUsuario);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el Usuario"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Usuario eliminado con éxito"));
    }
}
