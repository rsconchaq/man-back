package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.LocalDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<LocalDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarLocal");
        try {
            List<Object[]> results = query.getResultList();
            List<LocalDto> locales = (List<LocalDto>)results.stream().map(obj -> {
                LocalDto local = new LocalDto();
                local.setIdLocal((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                local.setDescripcion((String)obj[1]);
                local.setDireccion((String)obj[2]);
                local.setActivo((obj[3] != null) ? Integer.valueOf(((Boolean)obj[3]).booleanValue() ? 1 : 0) : null);
                return local;
            }).collect(Collectors.toList());
            return locales;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarLocal", e);
        }
    }

    public Integer registrar(LocalDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarLocal");
        query.registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Descripcion", param.getDescripcion().toUpperCase());
        query.setParameter("p_Direccion", param.getDireccion().toUpperCase());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarLocal", e);
        }
    }

    public Integer editar(LocalDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarLocal");
        query.registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdLocal", param.getIdLocal());
        query.setParameter("p_Descripcion", param.getDescripcion().toUpperCase());
        query.setParameter("p_Direccion", param.getDireccion().toUpperCase());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarLocal", e);
        }
    }

    public Integer eliminar(Integer idLocal) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarLocal");
        query.registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdLocal", idLocal);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarLocal", e);
        }
    }
}
