package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.PermisoDto;
import com.javainuse.bootmysqlcrud.service.RolPermisoService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/rolpermisos"})
public class RolPermisoController {
    @Autowired
    private RolPermisoService rolPermisoService;

    @GetMapping({"/obtener/{idRol}"})
    public ResponseEntity<ResponseWrapper> lista(@PathVariable Integer idRol) {
        List<PermisoDto> listaPermiso = this.rolPermisoService.obtener(idRol);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaPermiso, "Lista de Permisos"));
    }

    @PostMapping({"/actualizar"})
    public ResponseEntity<ResponseWrapper> obtener(@RequestBody List<PermisoDto> detalle) {
        Integer resultado = this.rolPermisoService.actualizar(detalle);
        if (resultado.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al actualizar permisos"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(resultado, "Permisos actualizados con éxito"));
    }
}
