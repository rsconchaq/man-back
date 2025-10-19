package com.javainuse.bootmysqlcrud.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class TokenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Integer validarToken(Integer idUsuario, String token) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("sp_validate_user_token");
        query.registerStoredProcedureParameter("p_token", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_valid", Integer.class, ParameterMode.OUT);
        query.setParameter("p_token", token);
        query.setParameter("p_user_id", idUsuario);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_valid");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerRoles", e);
        }
    }

    public Integer registrarToken(Integer idUsuario, String token, Date fechaExpiracion, String type) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("sp_register_user_token");
        query.registerStoredProcedureParameter("p_user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_token", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_expiration", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_type", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.OUT);
        query.setParameter("p_user_id", idUsuario);
        query.setParameter("p_token", token);
        query.setParameter("p_expiration", fechaExpiracion);
        query.setParameter("p_type", type);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_id");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar sp_register_user_token", e);
        }
    }
}
