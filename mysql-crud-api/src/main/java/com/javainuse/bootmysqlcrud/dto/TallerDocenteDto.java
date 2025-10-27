package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TallerDocenteDto {
    private Integer idAperturaTaller;
    private String nombreTaller;
    private Integer idDocente;
    private String codigoDocente;
    private String nombreDocente;
    private String descripcionEtapa;
    private String descripcionGrupo;
    private String descripcionTaller;
}
