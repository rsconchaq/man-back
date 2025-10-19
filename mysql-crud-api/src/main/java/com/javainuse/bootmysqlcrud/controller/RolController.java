package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.RolDto;
import com.javainuse.bootmysqlcrud.service.RolService;
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
@RequestMapping({"/api/v1/rol"})
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<RolDto> listaRol = this.rolService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaRol, "Lista de Roles"));
    }

    @GetMapping({"/obtener/{idRol}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idRol) {
        List<RolDto> listaRol = this.rolService.listar();
        RolDto rol = listaRol.stream().filter(r -> r.getIdRol().equals(idRol)).findFirst().orElse(null);
        if (rol == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Rol no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(rol, "Lista Rol"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody RolDto param) {
        Integer idRol = this.rolService.registrar(param);
        if (idRol == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el rol"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idRol, "Rol registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody RolDto param) {
        Integer idRol = this.rolService.editar(param);
        if (idRol == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el rol"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idRol, "Rol editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idRol}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idRol) {
        Integer result = this.rolService.eliminar(idRol);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el rol"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Rol eliminado con éxito"));
    }
}
