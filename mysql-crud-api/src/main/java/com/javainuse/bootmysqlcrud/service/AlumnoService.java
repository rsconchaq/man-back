package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.AlumnoDto;
import com.javainuse.bootmysqlcrud.dto.CuotaMatriculaDto;
import com.javainuse.bootmysqlcrud.dto.SeguimientoSesionDto;
import com.javainuse.bootmysqlcrud.dto.TalleresMatriculadosDto;

import java.util.List;

public interface AlumnoService {
    List<AlumnoDto> listar();

    Integer registrar(AlumnoDto paramAlumnoDto, String paramString);

    Integer editar(AlumnoDto paramAlumnoDto, String paramString);

    Integer eliminar(Integer paramInteger);

    List<TalleresMatriculadosDto> listarTalleresMatriculados(Integer paramInteger);

    List<SeguimientoSesionDto> listaSeguimientoTaller(Integer paramInteger);

    Integer actualizarSegimientoAlumno(SeguimientoSesionDto paramSeguimientoSesionDto, String paramString);

    List<CuotaMatriculaDto> listaCuotasMatricula(Integer paramInteger);

    Integer registrarCuotaMatricula(CuotaMatriculaDto paramCuotaMatriculaDto, String paramString);
    Integer registrarCuotaDetMatricula(CuotaMatriculaDto paramCuotaMatriculaDto, String paramString);
    List<SeguimientoSesionDto> ListarAlumnosDetallexSession(Integer idAperturaTallerDet, Integer idSesion);
}
