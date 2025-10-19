package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.EtapaDto;
import com.javainuse.bootmysqlcrud.service.EtapaService;
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
@RequestMapping({"/api/v1/etapa"})
public class EtapaController {
    @Autowired
    private EtapaService etapaService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<EtapaDto> listaEtapa = this.etapaService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaEtapa, "Lista de Etapas"));
    }

    @GetMapping({"/obtener/{idEtapa}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idEtapa) {
        List<EtapaDto> listaEtapa = this.etapaService.listar();
        EtapaDto etapa = listaEtapa.stream().filter(r -> r.getIdEtapa().equals(idEtapa)).findFirst().orElse(null);
        if (etapa == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Etapa no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(etapa, "Lista Etapa"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody EtapaDto param) {
        Integer idEtapa = this.etapaService.registrar(param);
        if (idEtapa == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el etapa"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idEtapa, "Etapa registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody EtapaDto param) {
        Integer idEtapa = this.etapaService.editar(param);
        if (idEtapa == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el etapa"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idEtapa, "Etapa editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idEtapa}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idEtapa) {
        Integer result = this.etapaService.eliminar(idEtapa);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el etapa"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Etapa eliminado con éxito"));
    }
}
