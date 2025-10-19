package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.AperturaTallerDto;
import com.javainuse.bootmysqlcrud.dto.MatriculaDto;
import com.javainuse.bootmysqlcrud.dto.TallerDto;
import com.javainuse.bootmysqlcrud.service.TallerService;
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
@RequestMapping({"/api/v1/taller"})
public class TallerController {
    @Autowired
    private TallerService tallerService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<TallerDto> listaTaller = this.tallerService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaTaller, "Lista de Talleres"));
    }

    @GetMapping({"/obtener/{idTaller}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idTaller) {
        List<TallerDto> listaTaller = this.tallerService.listar();
        TallerDto taller = listaTaller.stream().filter(r -> r.getIdTaller().equals(idTaller)).findFirst().orElse(null);
        if (taller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Taller no encontrado"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(taller, "Lista Taller"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody TallerDto param) {
        Integer idTaller = this.tallerService.registrar(param);
        if (idTaller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el taller"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idTaller, "Taller registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody TallerDto param) {
        Integer idTaller = this.tallerService.editar(param);
        if (idTaller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el taller"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(idTaller, "Taller editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idTaller}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idTaller) {
        Integer result = this.tallerService.eliminar(idTaller);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el taller"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(result, "Taller eliminado con éxito"));
    }

    @PostMapping({"/aperturaTaller"})
    public ResponseEntity<ResponseWrapper> aperturaTaller(@RequestBody AperturaTallerDto param) {
        if (param.getIdAperturaTaller() == null || param.getIdAperturaTaller().intValue() == 0) {
            Integer integer = this.tallerService.registrarAperturaTaller(param);
            if (integer == null)
                return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar la apertura del taller"));
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(integer, "Apertura del taller registrado con éxito"));
        }
        Integer idAperturaTaller = this.tallerService.actualizarAperturaTaller(param);
        if (idAperturaTaller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al actualizar la apertura del taller"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idAperturaTaller, "Apertura del taller actualizar con éxito"));
    }

    @GetMapping({"/listarAperturaTaller"})
    public ResponseEntity<ResponseWrapper> listarAperturaTaller() {
        List<AperturaTallerDto> listaAperturaTaller = this.tallerService.listarAperturaTaller();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaAperturaTaller, "Lista de Apertura de Talleres"));
    }

    @GetMapping({"/obtenerAperturaTallerId/{idAperturaTaller}"})
    public ResponseEntity<ResponseWrapper> obtenerAperturaTaller(@PathVariable Integer idAperturaTaller) {
        List<AperturaTallerDto> listaAperturaTaller = this.tallerService.listarAperturaTaller();
        //filtrar por idAperturaTaller
         AperturaTallerDto aperturaTaller = listaAperturaTaller.stream().filter(r -> r.getIdAperturaTaller().equals(idAperturaTaller)).findFirst().orElse(null);
         if (aperturaTaller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Apertura de Taller no encontrado"));

        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(aperturaTaller, "Lista de Apertura de Talleres"));
    }

    @PostMapping({"/registraMatricula"})
    public ResponseEntity<ResponseWrapper> registrarMatricula(@RequestBody MatriculaDto param) {
        Integer idAperturaTaller = this.tallerService.registrarMatricula(param);
        if (idAperturaTaller == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar la matricula"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idAperturaTaller, "Matricula registrado con éxito"));
    }

    @GetMapping({"/listarCalendarioTaller/{anio}/{mes}"})
    public ResponseEntity<ResponseWrapper> listarCalendarioTaller(@PathVariable Integer anio, @PathVariable String mes) {
        List<?> listaCalendarioTaller = this.tallerService.listarCalendarioTaller(anio, mes);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaCalendarioTaller, "Lista de Calendario de Talleres"));
    }
}
