package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.ApoderadoDto;
import com.javainuse.bootmysqlcrud.service.ApoderadoService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/apoderado"})
public class ApoderadoController {
    @Autowired
    private ApoderadoService apoderadoService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista(Integer idAlumno) {
        List<ApoderadoDto> listaApoderado = this.apoderadoService.listar(idAlumno);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaApoderado, "Lista de Apoderados"));
    }
}
