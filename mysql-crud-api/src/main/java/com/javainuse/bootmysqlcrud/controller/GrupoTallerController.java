package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.EtapaDetalleTallerDto;
import com.javainuse.bootmysqlcrud.dto.TallerDto;
import com.javainuse.bootmysqlcrud.service.EtapaGrupoDetalleService;
import com.javainuse.bootmysqlcrud.service.GrupoService;
import com.javainuse.bootmysqlcrud.service.TallerService;
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
@RequestMapping({"/api/v1/grupotaller"})
public class GrupoTallerController {
    @Autowired
    private EtapaGrupoDetalleService etapaGrupoDetalleService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private TallerService tallerService;

    @GetMapping({"/listar/{idEtapa}/{idGrupo}"})
    public ResponseEntity<ResponseWrapper> lista(@PathVariable Integer idEtapa, @PathVariable Integer idGrupo) {
        List<TallerDto> listaFinal = new ArrayList<>();
        List<TallerDto> listaCursos = this.tallerService.listar();
        List<EtapaDetalleTallerDto> listaNivelDetalleCurso = this.tallerService.listarAsignados();
        if (listaNivelDetalleCurso != null)
            listaNivelDetalleCurso = (List<EtapaDetalleTallerDto>)listaNivelDetalleCurso.stream().filter(x -> (x.getOEtapa().getIdEtapa().equals(idEtapa) && x.getOGrupo().getIdGrupo().equals(idGrupo))).collect(Collectors.toList());
        if (listaCursos != null && listaNivelDetalleCurso != null) {
            List<Integer> idsCursosAsignados = (List<Integer>)listaNivelDetalleCurso.stream().map(nd -> nd.getOTaller().getIdTaller()).collect(Collectors.toList());
            for (TallerDto c : listaCursos) {
                boolean encontrado = idsCursosAsignados.contains(c.getIdTaller());
                c.setAsignado(Integer.valueOf(encontrado ? 1 : 0));
                listaFinal.add(c);
            }
        }
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaFinal, "Lista de Grupo con Detalle"));
    }

    @PostMapping({"/asignarTaller"})
    public ResponseEntity<ResponseWrapper> asignarTaller(@RequestBody List<TallerDto> param) {
        Integer idTallerDetalle = this.tallerService.asignarTaller(param);
        if (idTallerDetalle == null)
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el taller detalle"));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body(new ResponseWrapper(idTallerDetalle, "Taller detalle registrado con éxito"));
    }

    @GetMapping({"/listaGruposyTalleres"})
    public ResponseEntity<ResponseWrapper> listaGruposyTalleres() {
        List<EtapaDetalleTallerDto> listaNivelDetalleCurso = this.tallerService.listarAsignados();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(new ResponseWrapper(listaNivelDetalleCurso, "Lista de Grupo con Detalle"));
    }
}
