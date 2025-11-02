package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.AlumnoDto;
import com.javainuse.bootmysqlcrud.dto.CuotaMatriculaDto;
import com.javainuse.bootmysqlcrud.dto.SeguimientoSesionDto;
import com.javainuse.bootmysqlcrud.dto.TalleresMatriculadosDto;
import com.javainuse.bootmysqlcrud.repository.AlumnoRepository;
import com.javainuse.bootmysqlcrud.repository.ApoderadoRepository;
import com.javainuse.bootmysqlcrud.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ApoderadoRepository apoderadoRepository;

    public List<AlumnoDto> listar() {
        return this.alumnoRepository.listar();
    }

    public Integer registrar(AlumnoDto param, String user) {
        return this.alumnoRepository.registrar(param, user);
    }

    public Integer editar(AlumnoDto param, String user) {
        return this.alumnoRepository.editar(param, user);
    }

    public Integer eliminar(Integer idAlumno) {
        return this.alumnoRepository.eliminar(idAlumno);
    }

    public List<TalleresMatriculadosDto> listarTalleresMatriculados(Integer idAlumno) {
        return this.alumnoRepository.listarTalleresMatriculados(idAlumno);
    }

    public List<SeguimientoSesionDto> listaSeguimientoTaller(Integer idMatricula) {
        return this.alumnoRepository.listaSeguimientoTaller(idMatricula);
    }

    public Integer actualizarSegimientoAlumno(SeguimientoSesionDto param, String user) {
        return this.alumnoRepository.actualizarSegimientoAlumno(param, user);
    }

    public List<CuotaMatriculaDto> listaCuotasMatricula(Integer idMatricula) {
        return this.alumnoRepository.listaCuotasMatricula(idMatricula);
    }

    public Integer registrarCuotaMatricula(CuotaMatriculaDto param, String user) {
        return this.alumnoRepository.registrarCuotaMatricula(param, user);
    }

    public Integer registrarCuotaDetMatricula(CuotaMatriculaDto param, String user) {
        return this.alumnoRepository.registrarCuotaDetMatricula(param, user);
    }

    public List<SeguimientoSesionDto> ListarAlumnosDetallexSession(Integer idAperturaTallerDet, Integer idSesion) {
        return this.alumnoRepository.ListarAlumnosDetallexSession(idAperturaTallerDet, idSesion);
    }
}

