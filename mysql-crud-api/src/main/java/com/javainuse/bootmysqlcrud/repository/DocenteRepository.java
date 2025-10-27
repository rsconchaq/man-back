package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.DocenteDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocenteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<DocenteDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarDocente");
        try {
            List<Object[]> results = query.getResultList();
            List<DocenteDto> docentesLista = (List<DocenteDto>)results.stream().map(obj -> {
                DocenteDto docente = new DocenteDto();
                docente.setIdDocente((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                docente.setCodigo((String)obj[1]);
                docente.setDocumentoIdentidad((String)obj[2]);
                docente.setNombres((String)obj[3]);
                docente.setApellidos((String)obj[4]);
                docente.setFechaNacimiento((obj[5] != null) ? (Date)obj[5] : null);
                docente.setSexo((String)obj[6]);
                docente.setCiudad((String)obj[7]);
                docente.setDireccion((String)obj[8]);
                docente.setGradoEstudio((String)obj[9]);
                docente.setEmail((String)obj[10]);
                docente.setNumeroTelefono((String)obj[11]);
                docente.setActivo((obj[12] != null) ? Integer.valueOf(((Boolean)obj[12]).booleanValue() ? 1 : 0) : null);
                return docente;
            }).collect(Collectors.toList());
            return docentesLista;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarDocente", e);
        }
    }

    public Integer registrar(DocenteDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarDocente");
        query.registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FechaNacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Sexo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Ciudad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_GradoEstudio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NumeroTelefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Nombres", param.getNombres() == null ? "" : param.getNombres().toUpperCase());
        query.setParameter("p_Apellidos", param.getApellidos() == null ? "" : param.getApellidos().toUpperCase());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_FechaNacimiento", param.getFechaNacimiento());
        query.setParameter("p_Sexo", param.getSexo() == null ? "" : param.getSexo().toUpperCase());
        query.setParameter("p_Ciudad", param.getCiudad() == null ? "" : param.getCiudad().toUpperCase());
        query.setParameter("p_Direccion", param.getDireccion() == null ? "" : param.getDireccion().toUpperCase());
        query.setParameter("p_GradoEstudio", param.getGradoEstudio() == null ? "" : param.getGradoEstudio().toUpperCase());
        query.setParameter("p_Email", param.getEmail() == null ? "" : param.getEmail().toUpperCase());
        query.setParameter("p_NumeroTelefono", param.getNumeroTelefono());
        query.setParameter("p_User", user);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarDocente", e);
        }
    }

    public Integer editar(DocenteDto param, String user) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarDocente");
        query.registerStoredProcedureParameter("p_IdDocente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FechaNacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Sexo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_GradoEstudio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Ciudad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NumeroTelefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdDocente", param.getIdDocente());
        query.setParameter("p_Nombres", param.getNombres() == null ? "" : param.getNombres().toUpperCase());
        query.setParameter("p_Apellidos", param.getApellidos() == null ? "" : param.getApellidos().toUpperCase());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_FechaNacimiento", param.getFechaNacimiento());
        query.setParameter("p_Sexo", param.getSexo() == null ? "" : param.getSexo().toUpperCase());
        query.setParameter("p_GradoEstudio", param.getGradoEstudio() == null ? "" : param.getGradoEstudio().toUpperCase());
        query.setParameter("p_Ciudad", param.getCiudad() == null ? "" : param.getCiudad().toUpperCase());
        query.setParameter("p_Direccion", param.getDireccion() == null ? "" : param.getDireccion().toUpperCase());
        query.setParameter("p_Email", param.getEmail() == null ? "" : param.getEmail().toUpperCase());
        query.setParameter("p_NumeroTelefono", param.getNumeroTelefono());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarDocente", e);
        }
    }

    public Integer eliminar(Integer idDocente) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarDocente");
        query.registerStoredProcedureParameter("p_IdDocente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdDocente", idDocente);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarDocente", e);
        }
    }
}
