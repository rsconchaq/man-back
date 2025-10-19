package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.AulaDto;
import com.javainuse.bootmysqlcrud.service.AulaService;
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
@RequestMapping({"/api/v1/aula"})
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<AulaDto> listaAula = this.aulaService.listar();
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAula, "Lista de Aulas"));
    }

    @GetMapping({"/obtener/{idAula}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idAula) {
        List<AulaDto> listaAula = this.aulaService.listar();
        AulaDto aula = listaAula.stream().filter(r -> r.getIdAula().equals(idAula)).findFirst().orElse(null);
        if (aula == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Aula no encontrado"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(aula, "Lista Aula"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody AulaDto param) {
        Integer idAula = this.aulaService.registrar(param);
        if (idAula == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el aula"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.CREATED).body(new ResponseWrapper(idAula, "Aula registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody AulaDto param) {
        Integer idAula = this.aulaService.editar(param);
        if (idAula == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el aula"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(idAula, "Aula editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idAula}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idAula) {
        Integer result = this.aulaService.eliminar(idAula);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el aula"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(result, "Aula eliminado con éxito"));
    }
}