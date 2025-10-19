package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.ExternalReniecDto;
import com.javainuse.bootmysqlcrud.service.ExternalApiService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/externalApi"})
public class ExternalApiController {
    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping({"/obtener/{numeroDocumento}"})
    public ResponseEntity<ResponseWrapper> lista(@PathVariable Integer numeroDocumento) {
        ExternalReniecDto informacion = this.externalApiService.consultarReniec(numeroDocumento.toString());
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(informacion, "Informacion del RENIEC obtenida"));
    }
}
