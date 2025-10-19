package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import com.javainuse.bootmysqlcrud.service.GrupoService;
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
@RequestMapping({"/api/v1/grupo"})
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<GrupoDto> listaGrupo = this.grupoService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaGrupo, "Lista de Grupos"));
    }

    @GetMapping({"/obtener/{idGrupo}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idGrupo) {
        List<GrupoDto> listaGrupo = this.grupoService.listar();
        GrupoDto grupo = listaGrupo.stream().filter(r -> r.getIdGrupo().equals(idGrupo)).findFirst().orElse(null);
        if (grupo == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Grupo no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(grupo, "Lista Grupo"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody GrupoDto param) {
        Integer idGrupo = this.grupoService.registrar(param);
        if (idGrupo == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el grupo"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idGrupo, "Grupo registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody GrupoDto param) {
        Integer idGrupo = this.grupoService.editar(param);
        if (idGrupo == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el grupo"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idGrupo, "Grupo editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idGrupo}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idGrupo) {
        Integer result = this.grupoService.eliminar(idGrupo);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el grupo"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Grupo eliminado con éxito"));
    }
}
