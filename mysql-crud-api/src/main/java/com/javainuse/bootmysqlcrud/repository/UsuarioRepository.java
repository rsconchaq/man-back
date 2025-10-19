package com.javainuse.bootmysqlcrud.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.dto.RolDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDetalleDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public int loginUsuario(String usuario, String clave) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_LoginUsuario").registerStoredProcedureParameter("p_Usuario", String.class, ParameterMode.IN).registerStoredProcedureParameter("p_Clave", String.class, ParameterMode.IN).registerStoredProcedureParameter("p_IdUsuario", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Usuario", usuario);
        query.setParameter("p_Clave", clave);
        query.execute();
        return ((Integer)query.getOutputParameterValue("p_IdUsuario")).intValue();
    }

    public UsuarioDetalleDto obtenerDetalleUsuario(Integer idUsuario) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerDetalleUsuario");
        query.registerStoredProcedureParameter("IdUsuario", Integer.class, ParameterMode.IN);
        query.setParameter("IdUsuario", idUsuario);
        Object result = query.getSingleResult();
        String json = result.toString();
        try {
            return (UsuarioDetalleDto)this.objectMapper.readValue(json, UsuarioDetalleDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a UsuarioDetalleDto", e);
        }
    }

    public UsuarioDto obtenerUsuarioXName(String usuario) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerUsuarioXName");
        query.registerStoredProcedureParameter("p_Usuario", String.class, ParameterMode.IN);
        query.setParameter("p_Usuario", usuario);
        Object result = query.getSingleResult();
        try {
            if (result instanceof Object[]) {
                Object[] row = (Object[])result;
                RolDto roldto = new RolDto();
                UsuarioDto dto = new UsuarioDto();
                dto.setIdUsuario((Integer)row[0]);
                dto.setNombres((String)row[1]);
                dto.setApellidos((String)row[2]);
                dto.setLoginUsuario((String)row[3]);
                dto.setLoginClave((String)row[4]);
                dto.setIdRol((Integer)row[5]);
                dto.setDescripcionReferencia((String)row[6]);
                int estado = Integer.parseInt(String.valueOf(row[8]));
                dto.setActivo(Integer.valueOf(estado));
                roldto.setIdRol((Integer)row[5]);
                roldto.setDescripcion((String)row[7]);
                roldto.setActivo(Integer.valueOf(estado));
                dto.setRol(roldto);
                return dto;
            }
            throw new RuntimeException("El SP no devolviel formato esperado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerUsuarioXName", e);
        }
    }

    public List<UsuarioDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerUsuario");
        try {
            List<Object[]> results = query.getResultList();

            List<UsuarioDto> usuarios = (List<UsuarioDto>)results.stream().map(obj -> {
                UsuarioDto rol = new UsuarioDto();
                rol.setIdUsuario((obj[0] != null) ? Integer.valueOf(((Number)obj[0]).intValue()) : null);
                rol.setNombres((String)obj[1]);
                rol.setApellidos((String)obj[2]);
                rol.setLoginUsuario((String)obj[3]);
                rol.setLoginClave((String)obj[4]);
                rol.setIdRol((Integer)obj[5]);
                rol.setDescripcionReferencia((String)obj[7]);
                rol.setActivo((obj[6] != null) ? Integer.valueOf(((Boolean)obj[6]).booleanValue() ? 1 : 0) : null);
                rol.setIdReferencia((Integer)obj[9]);
                RolDto roldto = new RolDto();
                roldto.setIdRol((Integer)obj[5]);
                roldto.setDescripcion((String)obj[8]);
                rol.setRol(roldto);
                return rol;
            }).collect(Collectors.toList());
            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerUsuario", e);
        }
    }

    public Integer registrar(UsuarioDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarUsuario");
        query.registerStoredProcedureParameter("Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("IdRol", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Usuario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Clave", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DescripcionReferencia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("IdReferencia", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("Nombres", param.getNombres().toUpperCase());
        query.setParameter("Apellidos", param.getApellidos().toUpperCase());
        query.setParameter("IdRol", param.getIdRol());
        query.setParameter("Usuario", param.getLoginUsuario());
        query.setParameter("Clave", param.getLoginClave());
        query.setParameter("DescripcionReferencia", param.getDescripcionReferencia().toUpperCase());
        query.setParameter("IdReferencia", param.getIdReferencia());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se Registro el usuario");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarUsuario", e);
        }
    }

    public Integer editar(UsuarioDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ModificarUsuario");
        query.registerStoredProcedureParameter("p_IdUsuario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Nombres", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Apellidos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdRol", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Usuario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Clave", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionReferencia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_IdReferencia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdUsuario", param.getIdUsuario());
        query.setParameter("p_Nombres", param.getNombres().toUpperCase());
        query.setParameter("p_Apellidos", param.getApellidos().toUpperCase());
        query.setParameter("p_IdRol", param.getIdRol());
        query.setParameter("p_Usuario", param.getLoginUsuario());
        query.setParameter("p_Clave", param.getLoginClave());
        query.setParameter("p_DescripcionReferencia", param.getDescripcionReferencia());
        query.setParameter("p_IdReferencia", param.getIdReferencia());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se actualizel usuario");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ModificarUsuario", e);
        }
    }

    public Integer eliminar(Integer idUsuario) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarUsuario");
        query.registerStoredProcedureParameter("p_IdUsuario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdUsuario", idUsuario);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se eliminel usuario");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarUsuario", e);
        }
    }

    public Integer actualizar_validar(String email, String password, String flag) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerUsuarioXEmail");
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_flag", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_email", email);
        query.setParameter("p_password", password);
        query.setParameter("p_flag", flag);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarUsuario", e);
        }
    }
}
