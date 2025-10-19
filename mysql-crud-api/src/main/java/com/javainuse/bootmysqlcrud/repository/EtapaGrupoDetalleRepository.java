package com.javainuse.bootmysqlcrud.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.dto.EtapaDetalleDto;
import com.javainuse.bootmysqlcrud.dto.EtapaDto;
import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EtapaGrupoDetalleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EtapaDetalleDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarEtapaDetalle");
        try {
            List<Object[]> results = query.getResultList();
            List<EtapaDetalleDto> talleres = (List<EtapaDetalleDto>)results.stream().map(obj -> {
                EtapaDetalleDto etapaDetalle = new EtapaDetalleDto();
                etapaDetalle.setIdEtapaDetalle((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                etapaDetalle.setActivo((obj[7] != null) ? Integer.valueOf(((Boolean)obj[7]).booleanValue() ? 1 : 0) : null);
                EtapaDto etapa = new EtapaDto();
                etapa.setIdEtapa((obj[1] != null) ? Integer.valueOf(((Integer)obj[1]).intValue()) : null);
                etapa.setDescripcionEtapa((String)obj[2]);
                etapa.setDescripcion((String)obj[3]);
                etapaDetalle.setEtapa(etapa);
                GrupoDto grupo = new GrupoDto();
                grupo.setIdGrupo((obj[4] != null) ? Integer.valueOf(((Integer)obj[4]).intValue()) : null);
                grupo.setDescripcionGrupo((String)obj[5]);
                grupo.setDescripcion((String)obj[6]);
                etapaDetalle.setGrupo(grupo);
                return etapaDetalle;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarEtapaDetalle", e);
        }
    }

    public Integer registrar(List<GrupoDto> param) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonLista = mapper.writeValueAsString(param);
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarEtapaDetalle");
            query.registerStoredProcedureParameter("p_Lista", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
            query.setParameter("p_Lista", jsonLista);
            query.execute();
            return (Integer)query.getOutputParameterValue("p_Resultado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarEtapaDetalle", e);
        }
    }
}
