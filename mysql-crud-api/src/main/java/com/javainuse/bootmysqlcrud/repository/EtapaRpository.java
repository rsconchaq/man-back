package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.EtapaDto;
import com.javainuse.bootmysqlcrud.dto.LocalDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EtapaRpository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EtapaDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarEtapa");
        try {
            List<Object[]> results = query.getResultList();
            List<EtapaDto> talleres = (List<EtapaDto>)results.stream().map(obj -> {
                EtapaDto etapa = new EtapaDto();
                etapa.setIdEtapa((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                etapa.setDescripcionEtapa((String)obj[3]);
                etapa.setDescripcion((String)obj[4]);
                etapa.setActivo((obj[5] != null) ? Integer.valueOf(((Boolean)obj[5]).booleanValue() ? 1 : 0) : null);
                LocalDto local = new LocalDto();
                local.setIdLocal((obj[1] != null) ? Integer.valueOf(((Integer)obj[1]).intValue()) : null);
                local.setDescripcion((String)obj[2]);
                etapa.setLocal(local);
                return etapa;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarEtapa", e);
        }
    }

    public Integer registrar(EtapaDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarEtapa");
        query.registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionEtapa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdLocal", param.getLocal().getIdLocal());
        query.setParameter("p_DescripcionEtapa",  param.getDescripcionEtapa() == null ? "" : param.getDescripcionEtapa().toUpperCase());
        query.setParameter("p_Descripcion",  param.getDescripcion() == null ? "" :param.getDescripcion().toUpperCase());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarEtapa", e);
        }
    }

    public Integer editar(EtapaDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarEtapa");
        query.registerStoredProcedureParameter("p_IdEtapa", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdLocal", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionEtapa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdEtapa", param.getIdEtapa());
        query.setParameter("p_IdLocal", param.getLocal().getIdLocal());
        query.setParameter("p_DescripcionEtapa", param.getDescripcionEtapa() == null ? "" : param.getDescripcionEtapa().toUpperCase());
        query.setParameter("p_Descripcion", param.getDescripcion() == null ? "" : param.getDescripcion().toUpperCase());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarEtapa", e);
        }
    }

    public Integer eliminar(Integer idEtapa) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarEtapa");
        query.registerStoredProcedureParameter("p_IdEtapa", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdEtapa", idEtapa);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarEtapa", e);
        }
    }
}
