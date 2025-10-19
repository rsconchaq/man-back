package com.javainuse.bootmysqlcrud.repository;

import com.javainuse.bootmysqlcrud.dto.ApoderadoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApoderadoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ApoderadoDto> listar(Integer idAlumno) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerApoderadoXAlumno");
        query.registerStoredProcedureParameter("p_idAlumno", Integer.class, ParameterMode.IN);
        query.setParameter("p_idAlumno", idAlumno);
        try {
            List<Object[]> results = query.getResultList();
            List<ApoderadoDto> apoderados = (List<ApoderadoDto>)results.stream().map(obj -> {
                ApoderadoDto aooderado = new ApoderadoDto();
                aooderado.setIdApoderado((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                aooderado.setIdAlumno((Integer)obj[1]);
                aooderado.setTipoRelacion((String)obj[2]);
                aooderado.setNombres((String)obj[3]);
                aooderado.setApellidos((String)obj[4]);
                aooderado.setDocumentoIdentidad((String)obj[5]);
                aooderado.setTelefono1((String)obj[6]);
                aooderado.setTelefono2((String)obj[7]);
                aooderado.setEmail((String)obj[8]);
                aooderado.setActivo((obj[9] != null) ? Integer.valueOf(((Boolean)obj[9]).booleanValue() ? 1 : 0) : null);
                return aooderado;
            }).collect(Collectors.toList());
            return apoderados;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerApoderadoXAlumno", e);
        }
    }

    public Integer registrar(ApoderadoDto param, String idUsuario) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarApoderado");
        query.registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono1", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono2", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_tipoRelacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdAlumno", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Nombres", param.getNombres());
        query.setParameter("p_Apellidos", param.getApellidos());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_Telefono1", param.getTelefono1());
        query.setParameter("p_Telefono2", param.getTelefono2());
        query.setParameter("p_Email", param.getEmail());
        query.setParameter("p_User", idUsuario);
        query.setParameter("p_tipoRelacion", param.getTipoRelacion());
        query.setParameter("p_IdAlumno", param.getIdAlumno());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron poderados");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer editar(ApoderadoDto param, String idUsuario) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarApoderado");
        query.registerStoredProcedureParameter("p_IdApoderado", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DocumentoIdentidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono1", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Telefono2", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_tipoRelacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdAlumno", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdApoderado", param.getIdApoderado());
        query.setParameter("p_Nombres", param.getNombres());
        query.setParameter("p_Apellidos", param.getApellidos());
        query.setParameter("p_DocumentoIdentidad", param.getDocumentoIdentidad());
        query.setParameter("p_Activo", param.getActivo());
        query.setParameter("p_Telefono1", param.getTelefono1());
        query.setParameter("p_Telefono2", param.getTelefono2());
        query.setParameter("p_Email", param.getEmail());
        query.setParameter("p_User", idUsuario);
        query.setParameter("p_tipoRelacion", param.getTipoRelacion());
        query.setParameter("p_IdAlumno", param.getIdAlumno());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarApoderado", e);
        }
    }

    public Integer eliminar(Integer idApoderado) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarApoderado");
        query.registerStoredProcedureParameter("p_IdApoderado", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdApoderado", idApoderado);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron apoderado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarApoderado", e);
        }
    }
}
