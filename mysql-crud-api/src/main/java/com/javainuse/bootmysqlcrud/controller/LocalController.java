package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.LocalDto;
import com.javainuse.bootmysqlcrud.service.LocalService;
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
@RequestMapping({"/api/v1/local"})
public class LocalController {
    @Autowired
    private LocalService localService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<LocalDto> listaLocal = this.localService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaLocal, "Lista de Locals"));
    }

    @GetMapping({"/obtener/{idLocal}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idLocal) {
        List<LocalDto> listaLocal = this.localService.listar();
        LocalDto local = listaLocal.stream().filter(r -> r.getIdLocal().equals(idLocal)).findFirst().orElse(null);
        if (local == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Local no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(local, "Lista Local"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody LocalDto param) {
        Integer idLocal = this.localService.registrar(param);
        if (idLocal == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el local"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idLocal, "Local registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody LocalDto param) {
        Integer idLocal = this.localService.editar(param);
        if (idLocal == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el local"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idLocal, "Local editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idLocal}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idLocal) {
        Integer result = this.localService.eliminar(idLocal);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el local"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Local eliminado con éxito"));
    }
}