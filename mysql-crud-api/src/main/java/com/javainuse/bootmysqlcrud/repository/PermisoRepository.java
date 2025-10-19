package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.PermisoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PermisoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PermisoDto> obtener(Integer IdRol) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerPermisos");
        query.registerStoredProcedureParameter("IdRol", Integer.class, ParameterMode.IN);
        query.setParameter("IdRol", IdRol);
        try {
            List<Object[]> results = query.getResultList();
            List<PermisoDto> permisos = (List<PermisoDto>)results.stream().map(obj -> {
                PermisoDto permiso = new PermisoDto();
                permiso.setIdPermisos((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                permiso.setMenu((String)obj[1]);
                permiso.setSubMenu((String)obj[2]);
                permiso.setActivo((obj[2] != null) ? Integer.valueOf(((Boolean)obj[3]).booleanValue() ? 1 : 0) : null);
                return permiso;
            }).collect(Collectors.toList());
            return permisos;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerPermisos", e);
        }
    }

    public Integer actualizar(Integer activo, Integer idPermisos) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ActualizarPermisos");
        query.registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdPermisos", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Activo", activo);
        query.setParameter("p_IdPermisos", idPermisos);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron roles");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }
}
