package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.*;
import com.javainuse.bootmysqlcrud.repository.TallerRepository;
import com.javainuse.bootmysqlcrud.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallerServiceImpl implements TallerService {
    @Autowired
    private TallerRepository tallerRepository;

    public List<TallerDto> listar() {
        return this.tallerRepository.listar();
    }

    public Integer registrar(TallerDto param) {
        return this.tallerRepository.registrar(param);
    }

    public Integer editar(TallerDto param) {
        return this.tallerRepository.editar(param);
    }

    public Integer eliminar(Integer idTaller) {
        return this.tallerRepository.eliminar(idTaller);
    }

    public List<EtapaDetalleTallerDto> listarAsignados() {
        return this.tallerRepository.listarAsignados();
    }

    public Integer asignarTaller(List<TallerDto> param) {
        return this.tallerRepository.asignarTaller(param);
    }

    public Integer registrarAperturaTaller(AperturaTallerDto param) {
        return this.tallerRepository.registrarAperturaTaller(param);
    }

    public Integer actualizarAperturaTaller(AperturaTallerDto param) {
        return this.tallerRepository.actualizarAperturaTaller(param);
    }

    public List<AperturaTallerDto> listarAperturaTaller() {
        return this.tallerRepository.listarAperturaTaller();
    }

    public Integer registrarMatricula(MatriculaDto param) {
        return this.tallerRepository.registrarMatricula(param);
    }
    public List<CalendarioTallerDto> listarCalendarioTaller(Integer edad, Integer idAlumno) {
        return this.tallerRepository.listarCalendarioTaller(edad, idAlumno);
    }
}
