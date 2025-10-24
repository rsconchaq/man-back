package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.*;

import java.util.List;

public interface TallerService {
    List<TallerDto> listar();

    Integer registrar(TallerDto paramTallerDto);

    Integer editar(TallerDto paramTallerDto);

    Integer eliminar(Integer paramInteger);

    List<EtapaDetalleTallerDto> listarAsignados();

    Integer asignarTaller(List<TallerDto> paramList);

    Integer registrarAperturaTaller(AperturaTallerDto paramAperturaTallerDto);

    Integer actualizarAperturaTaller(AperturaTallerDto paramAperturaTallerDto);

    List<AperturaTallerDto> listarAperturaTaller();

    Integer registrarMatricula(MatriculaDto paramMatriculaDto);
    List<CalendarioTallerDto> listarCalendarioTaller(Integer edad, Integer idAlumno);
}
