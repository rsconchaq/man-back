package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.RolDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RolRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<RolDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerRoles");
        try {
            List<Object[]> results = query.getResultList();
            List<RolDto> roles = (List<RolDto>)results.stream().map(obj -> {
                RolDto rol = new RolDto();
                rol.setIdRol((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                rol.setDescripcion((String)obj[1]);
                rol.setActivo((obj[2] != null) ? Integer.valueOf(((Boolean)obj[2]).booleanValue() ? 1 : 0) : null);
                return rol;
            }).collect(Collectors.toList());
                return roles;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer registrar(RolDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarRol");
        query.registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Descripcion", param.getDescripcion().toUpperCase());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
                return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer editar(RolDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ModificarRol");
        query.registerStoredProcedureParameter("p_IdRol", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdRol", param.getIdRol());
        query.setParameter("p_Descripcion", param.getDescripcion().toUpperCase());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer eliminar(Integer idRol) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarRol");
        query.registerStoredProcedureParameter("p_IdRol", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdRol", idRol);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
                return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }
}
