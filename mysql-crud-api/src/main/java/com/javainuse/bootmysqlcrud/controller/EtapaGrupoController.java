package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.EtapaDetalleDto;
import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import com.javainuse.bootmysqlcrud.service.EtapaGrupoDetalleService;
import com.javainuse.bootmysqlcrud.service.GrupoService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping({"/api/v1/etapaGrupo"})
public class EtapaGrupoController {
    @Autowired
    private EtapaGrupoDetalleService etapaGrupoDetalleService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping({"/listar/{idEtapa}"})
    public ResponseEntity<ResponseWrapper> lista(@PathVariable Integer idEtapa) {
        List<GrupoDto> listaFinal = new ArrayList<>();
        List<GrupoDto> listaGrado = this.grupoService.listar();
        List<EtapaDetalleDto> listaEtapaDetalle = this.etapaGrupoDetalleService.listar();
        if (listaEtapaDetalle != null && idEtapa.intValue() > 0)
            listaEtapaDetalle = (List<EtapaDetalleDto>)listaEtapaDetalle.stream().filter(x -> x.getEtapa().getIdEtapa().equals(idEtapa)).collect(Collectors.toList());
        if (listaGrado != null && listaEtapaDetalle != null) {
            List<GrupoDto> listaAsignados = (List<GrupoDto>)listaEtapaDetalle.stream().map(EtapaDetalleDto::getGrupo).collect(Collectors.toList());
            for (GrupoDto g : listaGrado) {
                boolean encontrado = listaAsignados.stream().anyMatch(asig -> asig.getIdGrupo().equals(g.getIdGrupo()));
                g.setAsignado(Integer.valueOf(encontrado ? 1 : 0));
                listaFinal.add(g);
            }
        }
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaFinal, "Lista de Etapas con Detalle"));
    }

    @GetMapping({"/listarGrupoDetalle/{idEtapa}"})
    public ResponseEntity<ResponseWrapper> listaGrupoDetalle(@PathVariable Integer idEtapa) {
        List<EtapaDetalleDto> listaEtapaDetalle = this.etapaGrupoDetalleService.listar();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaEtapaDetalle, "Lista de Etapas con Detalle"));
    }

    @PostMapping({"/guardar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody List<GrupoDto> param) {
        Integer idEtapaDetalle = this.etapaGrupoDetalleService.registrar(param);
        if (idEtapaDetalle == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el etapa detalle"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idEtapaDetalle, "Etapa detalle registrado con éxito"));
    }
}
