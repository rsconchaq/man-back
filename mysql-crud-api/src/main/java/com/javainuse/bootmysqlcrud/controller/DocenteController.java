package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.DocenteDto;
import com.javainuse.bootmysqlcrud.service.DocenteService;
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
@RequestMapping({"/api/v1/docente"})
public class DocenteController {
    @Autowired
    private DocenteService docenteService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<DocenteDto> listaDocente = this.docenteService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaDocente, "Lista de Docentes"));
    }

    @GetMapping({"/obtener/{idDocente}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idDocente) {
        List<DocenteDto> listaDocente = this.docenteService.listar();
        DocenteDto docente = listaDocente.stream().filter(r -> r.getIdDocente().equals(idDocente)).findFirst().orElse(null);
        if (docente == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Docente no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(docente, "Lista Docente"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody DocenteDto param) {
        Integer idDocente = this.docenteService.registrar(param, "system");
        if (idDocente == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el docente"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idDocente, "Docente registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody DocenteDto param) {
        Integer idDocente = this.docenteService.editar(param, "system");
        if (idDocente == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el docente"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idDocente, "Docente editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idDocente}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idDocente) {
        Integer result = this.docenteService.eliminar(idDocente);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el docente"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Docente eliminado con éxito"));
    }
}
