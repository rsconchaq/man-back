package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AperturaTallerDto {
    private Integer idAperturaTaller;

    private Integer idEtapaDetalleTaller;

    private Integer idEtapa;

    private Integer idGrupo;

    private Integer idTaller;

    private Integer idAula;

    private String descripcionTaller;

    private String diaSemana;

    private String horaInicio;

    private String horaFin;

    private String fechaInicio;

    private String fechaFin;

    private Integer total_vacantes;

    private Integer vacantes_disponible;

    private Integer vacantes_ocupadas;

    private Integer vacantes_opcionales;

    private String descripcionEtapa;

    private String descripcionGrupo;

    private String descripcionAula;

    private String descripcionLocal;

    private Integer apertura;

    private Integer estado;
    private Integer idDocente;

    private List<AperturaTallerDetDto> fechas;
}
