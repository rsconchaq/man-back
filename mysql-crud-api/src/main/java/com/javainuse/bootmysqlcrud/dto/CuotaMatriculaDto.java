package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CuotaMatriculaDto {
    private int flag;
    private int idCuota;
    private int numeroCuota;
    private int nroDivision;
    private BigDecimal monto;
    private String fechaVencimiento;
    private String fechaPago;
    private String estadoPago;
    private String observacion;
    private int idMatricula;
    private int idAlumno;
    private String nombres;
    private String apellidos;
    private String descripcionGrupo;
    private String descripcionEtapa;
    private String local;
    private Boolean seleccionadoPagar;
    private Boolean seleccionadoBoleta;
    private int tipoPago;
    private String divisiones;
}
