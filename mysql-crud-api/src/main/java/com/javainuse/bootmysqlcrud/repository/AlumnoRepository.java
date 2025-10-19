package com.javainuse.bootmysqlcrud.repository;

import com.javainuse.bootmysqlcrud.dto.AlumnoDto;
import com.javainuse.bootmysqlcrud.dto.CuotaMatriculaDto;
import com.javainuse.bootmysqlcrud.dto.SeguimientoSesionDto;
import com.javainuse.bootmysqlcrud.dto.TalleresMatriculadosDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AlumnoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AlumnoDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarAlumno");
        try {
            List<Object[]> results = query.getResultList();
            List<AlumnoDto> alumnosLista = (List<AlumnoDto>)results.stream().map(obj -> {
                AlumnoDto alumno = new AlumnoDto();
                alumno.setIdAlumno((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                alumno.setCodigo((String)obj[1]);
                alumno.setNombres((String)obj[2]);
                alumno.setApellidos((String)obj[3]);
                alumno.setDocumentoIdentidad((String)obj[4]);
                alumno.setFechaNacimiento((obj[5] != null) ? (Date)obj[5] : null);
                alumno.setSexo((String)obj[6]);
                alumno.setCiudad((String)obj[7]);
                alumno.setDireccion((String)obj[8]);
                alumno.setActivo((obj[9] != null) ? Integer.valueOf(((Boolean)obj[9]).booleanValue() ? 1 : 0) : null);
                alumno.setTelefono((String)obj[10]);
                alumno.setEmail((String)obj[11]);
                alumno.setEdad((obj[12] != null) ? Integer.valueOf(((Number)obj[12]).intValue()) : null);
                alumno.setDiagnostico((String)obj[13]);
                alumno.setObservaciones((String)obj[14]);
                return alumno;
            }).collect(Collectors.toList());
            return alumnosLista;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarAlumno", e);
        }
    }

    public Integer registrar(AlumnoDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarAlumno");
        query.registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FechaNacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Sexo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Ciudad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Diagnostico", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Comentario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Nombres", param.getNombres());
        query.setParameter("p_Apellidos", param.getApellidos());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_FechaNacimiento", param.getFechaNacimiento());
        query.setParameter("p_Sexo", param.getSexo());
        query.setParameter("p_Ciudad", param.getCiudad());
        query.setParameter("p_Direccion", param.getDireccion());
        query.setParameter("p_Telefono", param.getTelefono());
        query.setParameter("p_Email", param.getEmail());
        query.setParameter("p_Edad", param.getEdad());
        query.setParameter("p_User", user);
        query.setParameter("p_Diagnostico", param.getDiagnostico());
        query.setParameter("p_Comentario", param.getObservaciones());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer editar(AlumnoDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarAlumno");
        query.registerStoredProcedureParameter("p_IdAlumno", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Codigo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FechaNacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Sexo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Ciudad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Diagnostico", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Comentario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdAlumno", param.getIdAlumno());
        query.setParameter("p_Codigo", param.getCodigo());
        query.setParameter("p_Nombres", param.getNombres());
        query.setParameter("p_Apellidos", param.getApellidos());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_FechaNacimiento", param.getFechaNacimiento());
        query.setParameter("p_Sexo", param.getSexo());
        query.setParameter("p_Ciudad", param.getCiudad());
        query.setParameter("p_Direccion", param.getDireccion());
        query.setParameter("p_Activo", param.getActivo());
        query.setParameter("p_Telefono", param.getTelefono());
        query.setParameter("p_Email", param.getEmail());
        query.setParameter("p_Edad", param.getEdad());
        query.setParameter("p_User", user);
        query.setParameter("p_Diagnostico", param.getDiagnostico());
        query.setParameter("p_Comentario", param.getObservaciones());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron roles");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer eliminar(Integer idRol) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarAlumno");
        query.registerStoredProcedureParameter("p_IdAlumno", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdAlumno", idRol);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron roles");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public List<TalleresMatriculadosDto> listarTalleresMatriculados(Integer idAlumno) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("ListaTalleresMatriculadosxalumno");
        query.registerStoredProcedureParameter("p_IdAlumno", Integer.class, ParameterMode.IN);
        query.setParameter("p_IdAlumno", idAlumno);
        try {
            List<Object[]> results = query.getResultList();
            List<TalleresMatriculadosDto> listaTalleresMatriculados = (List<TalleresMatriculadosDto>)results.stream().map(obj -> {
                TalleresMatriculadosDto lista = new TalleresMatriculadosDto();
                lista.setIdEtapa((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                lista.setIdGrupo((obj[1] != null) ? Integer.valueOf(((Number)obj[1]).intValue()) : null);
                lista.setIdAlumno((obj[2] != null) ? Integer.valueOf(((Number)obj[2]).intValue()) : null);
                lista.setIdMatricula((obj[3] != null) ? Integer.valueOf(((Number)obj[3]).intValue()) : null);
                lista.setDescripcionLocal((obj[4] != null) ? (String)obj[4] : null);
                lista.setDescripcionEtapa((obj[5] != null) ? (String)obj[5] : null);
                lista.setDescripcionGrupo((obj[6] != null) ? (String)obj[6] : null);
                lista.setNombresAlumno((obj[7] != null) ? (String)obj[7] : null);
                lista.setApellidosAlumno((obj[8] != null) ? (String)obj[8] : null);
                lista.setDescripcion((obj[9] != null) ? (String)obj[9] : null);
                lista.setFechaInicio((obj[10] != null) ? (Date)obj[10] : null);
                lista.setFechaFin((obj[11] != null) ? (Date)obj[11] : null);
                return lista;
            }).collect(Collectors.toList());
            return listaTalleresMatriculados;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar ListaTalleresMatriculadosxalumno", e);
        }
    }

    public List<SeguimientoSesionDto> listaSeguimientoTaller(Integer idMatricula) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarSeguimientoAlumno");
        query.registerStoredProcedureParameter("p_idMatricula", Integer.class, ParameterMode.IN);
        query.setParameter("p_idMatricula", idMatricula);
        try {
            List<Object[]> results = query.getResultList();
            List<SeguimientoSesionDto> listaSeguimientoTaller = (List<SeguimientoSesionDto>)results.stream().map(obj -> {
                SeguimientoSesionDto lista = new SeguimientoSesionDto();
                lista.setIdSeguimientoSesion(((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null).intValue());
                lista.setAsistencia((obj[1] != null) ? (String)obj[1] : null);
                lista.setObservacion((obj[2] != null) ? (String)obj[2] : null);
                lista.setFechaRegistro((obj[3] != null) ? (Date)obj[3] : null);
                lista.setIdAperturaTallerDet(((obj[4] != null) ? Integer.valueOf(((Number)obj[4]).intValue()) : null).intValue());
                lista.setDescripcionSesion((obj[5] != null) ? (String)obj[5] : null);
                lista.setFechaInicio((obj[6] != null) ? (Date)obj[6] : null);
                lista.setFechaFin((obj[7] != null) ? (Date)obj[7] : null);
                lista.setTaller((obj[8] != null) ? (String)obj[8] : null);
                lista.setDescripcionGrupo((obj[9] != null) ? (String)obj[9] : null);
                lista.setDescripcionEtapa((obj[10] != null) ? (String)obj[10] : null);
                lista.setIdAlumno(((obj[11] != null) ? Integer.valueOf(((Number)obj[11]).intValue()) : null).intValue());
                lista.setNombres((obj[12] != null) ? (String)obj[12] : null);
                lista.setApellidos((obj[13] != null) ? (String)obj[13] : null);
                lista.setSeguimiento1(((obj[14] != null) ? Integer.valueOf(((Number)obj[14]).intValue()) : null).intValue());
                lista.setSeguimiento2(((obj[15] != null) ? Integer.valueOf(((Number)obj[15]).intValue()) : null).intValue());
                lista.setSeguimiento3(((obj[16] != null) ? Integer.valueOf(((Number)obj[16]).intValue()) : null).intValue());
                lista.setSeguimiento4(((obj[17] != null) ? Integer.valueOf(((Number)obj[17]).intValue()) : null).intValue());
                lista.setSeguimiento5(((obj[18] != null) ? Integer.valueOf(((Number)obj[18]).intValue()) : null).intValue());
                lista.setSeguimiento6(((obj[19] != null) ? Integer.valueOf(((Number)obj[19]).intValue()) : null).intValue());
                lista.setSeguimiento7(((obj[20] != null) ? Integer.valueOf(((Number)obj[20]).intValue()) : null).intValue());
                lista.setSeguimiento8(((obj[21] != null) ? Integer.valueOf(((Number)obj[21]).intValue()) : null).intValue());
                lista.setSeguimiento9(((obj[22] != null) ? Integer.valueOf(((Number)obj[22]).intValue()) : null).intValue());
                lista.setSeguimiento10(((obj[23] != null) ? Integer.valueOf(((Number)obj[23]).intValue()) : null).intValue());
                lista.setIdMatricula(((obj[24] != null) ? Integer.valueOf(((Number)obj[24]).intValue()) : null).intValue());
                return lista;
            }).collect(Collectors.toList());
            return listaSeguimientoTaller;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarSeguimientoAlumno", e);
        }
    }

    public Integer actualizarSegimientoAlumno(SeguimientoSesionDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ActualizarSeguimientoAlumno");
        query.registerStoredProcedureParameter("p_idSeguimientoSesion", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_observacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_asistencia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento1", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento2", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento3", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento4", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento5", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento6", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento7", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento8", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento9", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seguimiento10", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_idSeguimientoSesion", Integer.valueOf(param.getIdSeguimientoSesion()));
        query.setParameter("p_observacion", param.getObservacion());
        query.setParameter("p_asistencia", param.getAsistencia());
        query.setParameter("p_seguimiento1", Integer.valueOf(param.getSeguimiento1()));
        query.setParameter("p_seguimiento2", Integer.valueOf(param.getSeguimiento2()));
        query.setParameter("p_seguimiento3", Integer.valueOf(param.getSeguimiento3()));
        query.setParameter("p_seguimiento4", Integer.valueOf(param.getSeguimiento4()));
        query.setParameter("p_seguimiento5", Integer.valueOf(param.getSeguimiento5()));
        query.setParameter("p_seguimiento6", Integer.valueOf(param.getSeguimiento6()));
        query.setParameter("p_seguimiento7", Integer.valueOf(param.getSeguimiento7()));
        query.setParameter("p_seguimiento8", Integer.valueOf(param.getSeguimiento8()));
        query.setParameter("p_seguimiento9", Integer.valueOf(param.getSeguimiento9()));
        query.setParameter("p_seguimiento10", Integer.valueOf(param.getSeguimiento10()));
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron roles");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ActualizarSeguimientoAlumno", e);
        }
    }

    public List<CuotaMatriculaDto> listaCuotasMatricula(Integer idMatricula) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarCuotasMatricula");
        query.registerStoredProcedureParameter("p_idMatricula", Integer.class, ParameterMode.IN);
        query.setParameter("p_idMatricula", idMatricula);
        try {
            List<Object[]> results = query.getResultList();
            List<CuotaMatriculaDto> listaSeguimientoTaller = (List<CuotaMatriculaDto>)results.stream().map(obj -> {
                CuotaMatriculaDto lista = new CuotaMatriculaDto();
                lista.setIdCuota(((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null).intValue());
                lista.setNumeroCuota(((obj[1] != null) ? Integer.valueOf(((Number)obj[1]).intValue()) : null).intValue());
                lista.setMonto((obj[2] != null) ? (BigDecimal)obj[2] : null);
                lista.setFechaVencimiento((obj[3] != null) ? (Date)obj[3] : null);
                lista.setFechaPago((obj[4] != null) ? (Date)obj[4] : null);
                lista.setEstadoPago((obj[5] != null) ? (String)obj[5] : null);
                lista.setObservacion((obj[6] != null) ? (String)obj[6] : null);
                lista.setIdMatricula(((obj[7] != null) ? Integer.valueOf(((Number)obj[7]).intValue()) : null).intValue());
                lista.setIdAlumno(((obj[8] != null) ? Integer.valueOf(((Number)obj[8]).intValue()) : null).intValue());
                lista.setNombres((obj[9] != null) ? (String)obj[9] : null);
                lista.setApellidos((obj[10] != null) ? (String)obj[10] : null);
                lista.setDescripcionGrupo((obj[11] != null) ? (String)obj[11] : null);
                lista.setDescripcionEtapa((obj[12] != null) ? (String)obj[12] : null);
                lista.setLocal((obj[13] != null) ? (String)obj[13] : null);
                lista.setDivisiones((obj[14] != null) ? (String)obj[14] : null);
                return lista;
            }).collect(Collectors.toList());
            return listaSeguimientoTaller;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarCuotasMatricula", e);
        }
    }

    public Integer registrarCuotaMatricula(CuotaMatriculaDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarCuotaMatriculaAlumno");
        query.registerStoredProcedureParameter("p_flag", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_idCuota", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_numeroCuota", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_idMatricula", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_observacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_estadoPago", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_monto", BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fechaVencimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_flag", Integer.valueOf(param.getFlag()));
        query.setParameter("p_idCuota", Integer.valueOf(param.getIdCuota()));
        query.setParameter("p_numeroCuota", Integer.valueOf(param.getNumeroCuota()));
        query.setParameter("p_idMatricula", Integer.valueOf(param.getIdMatricula()));
        query.setParameter("p_observacion", param.getObservacion());
        query.setParameter("p_estadoPago", param.getEstadoPago());
        query.setParameter("p_monto", param.getMonto());
        Date fecha = param.getFechaVencimiento();
        Timestamp fechaTimestamp = (fecha != null) ? new Timestamp(fecha.getTime()) : null;
        query.setParameter("p_fechaVencimiento", fechaTimestamp);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarCuotaMatriculaAlumno", e);
        }
    }

    public Integer registrarCuotaDetMatricula(CuotaMatriculaDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarCuotaMatriculaDetAlumno");
        query.registerStoredProcedureParameter("p_flag", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_idCuota", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_numeroCuota", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_idMatricula", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_observacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_estadoPago", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_monto", BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fechaVencimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seleccionadoPagar", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_seleccionarBoleta", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_tipoPago", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_flag", Integer.valueOf(param.getFlag()));
        query.setParameter("p_idCuota", Integer.valueOf(param.getIdCuota()));
        query.setParameter("p_numeroCuota", Integer.valueOf(param.getNumeroCuota()));
        query.setParameter("p_idMatricula", Integer.valueOf(param.getIdMatricula()));
        query.setParameter("p_observacion", param.getObservacion());
        query.setParameter("p_estadoPago", param.getEstadoPago());
        query.setParameter("p_monto", param.getMonto());
        Date fecha = param.getFechaVencimiento();
        Timestamp fechaTimestamp = (fecha != null) ? new Timestamp(fecha.getTime()) : null;
        query.setParameter("p_fechaVencimiento", fechaTimestamp);
        query.setParameter("p_seleccionadoPagar", param.getSeleccionadoPagar());
        query.setParameter("p_seleccionarBoleta", param.getSeleccionadoBoleta());
        query.setParameter("p_tipoPago", Integer.valueOf(param.getTipoPago()));
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarCuotaDetMatriculaAlumno", e);
        }
    }
}
