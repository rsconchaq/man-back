package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.AulaDto;
import com.javainuse.bootmysqlcrud.dto.LocalDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AulaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AulaDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarAula");
        try {
            List<Object[]> results = query.getResultList();
            List<AulaDto> talleres = (List<AulaDto>)results.stream().map(obj -> {
                AulaDto aula = new AulaDto();
                aula.setIdAula((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                aula.setDescripcionAula((String)obj[3]);
                aula.setDescripcion((String)obj[4]);
                aula.setActivo((obj[5] != null) ? Integer.valueOf(((Boolean)obj[5]).booleanValue() ? 1 : 0) : null);
                LocalDto local = new LocalDto();
                local.setIdLocal((obj[1] != null) ? Integer.valueOf(((Integer)obj[1]).intValue()) : null);
                local.setDescripcion((String)obj[2]);
                aula.setLocal(local);
                return aula;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarAula", e);
        }
    }

    public Integer registrar(AulaDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarAula");
        query.registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionAula", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdLocal", param.getLocal().getIdLocal());
        query.setParameter("p_DescripcionAula", param.getDescripcionAula() == null ? "" : param.getDescripcionAula().toUpperCase());
        query.setParameter("p_Descripcion", param.getDescripcion() == null ? "" : param.getDescripcion().toUpperCase());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarAula", e);
        }
    }

    public Integer editar(AulaDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarAula");
        query.registerStoredProcedureParameter("p_IdAula", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionAula", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdAula", param.getIdAula());
        query.setParameter("p_IdLocal", param.getLocal().getIdLocal());
        query.setParameter("p_DescripcionAula", param.getDescripcionAula() == null ? "" : param.getDescripcionAula().toUpperCase());
        query.setParameter("p_Descripcion", param.getDescripcion() == null ? "" : param.getDescripcion().toUpperCase());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarAula", e);
        }
    }

    public Integer eliminar(Integer idAula) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarAula");
        query.registerStoredProcedureParameter("p_IdAula", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdAula", idAula);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarAula", e);
        }
    }
}
