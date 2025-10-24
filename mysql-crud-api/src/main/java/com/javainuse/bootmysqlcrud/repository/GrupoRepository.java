package com.javainuse.bootmysqlcrud.repository;
import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GrupoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<GrupoDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarGrupo");
        try {
            List<Object[]> results = query.getResultList();
            List<GrupoDto> talleres = (List<GrupoDto>)results.stream().map(obj -> {
                GrupoDto rol = new GrupoDto();
                rol.setIdGrupo((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                rol.setDescripcionGrupo((String)obj[1]);
                rol.setDescripcion((String)obj[2]);
                rol.setEdad1((obj[3] != null) ? Integer.valueOf(((Integer)obj[3]).intValue()) : null);
                rol.setEdad2((obj[4] != null) ? Integer.valueOf(((Integer)obj[4]).intValue()) : null);
                rol.setActivo((obj[5] != null) ? Integer.valueOf(((Boolean)obj[5]).booleanValue() ? 1 : 0) : null);
                return rol;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarGrupo", e);
        }
    }

    public Integer registrar(GrupoDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarGrupo");
        query.registerStoredProcedureParameter("p_DescripcionGrupo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad1", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad2", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_DescripcionGrupo", param.getDescripcionGrupo().toUpperCase());
        query.setParameter("p_Descripcion", param.getDescripcion());
        query.setParameter("p_Edad1", param.getEdad1());
        query.setParameter("p_Edad2", param.getEdad2());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarGrupo", e);
        }
    }

    public Integer editar(GrupoDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EditarGrupo");
        query.registerStoredProcedureParameter("p_IdGrupo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionGrupo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad1", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Edad2", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdGrupo", param.getIdGrupo());
        query.setParameter("p_DescripcionGrupo", param.getDescripcionGrupo().toUpperCase());
        query.setParameter("p_Descripcion", param.getDescripcion());
        query.setParameter("p_Edad1", param.getEdad1());
        query.setParameter("p_Edad2", param.getEdad2());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EditarGrupo", e);
        }
    }

    public Integer eliminar(Integer idGrupo) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarGrupo");
        query.registerStoredProcedureParameter("p_IdGrupo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdGrupo", idGrupo);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarGrupo", e);
        }
    }
}
