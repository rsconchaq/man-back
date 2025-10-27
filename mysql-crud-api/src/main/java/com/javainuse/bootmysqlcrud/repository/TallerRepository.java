package com.javainuse.bootmysqlcrud.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.dto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TallerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<TallerDto> listar() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerTalleres");
        try {
            List<Object[]> results = query.getResultList();
            List<TallerDto> talleres = (List<TallerDto>)results.stream().map(obj -> {
                TallerDto rol = new TallerDto();
                rol.setIdTaller((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                rol.setDescripcion((String)obj[1]);
                rol.setActivo((obj[2] != null) ? Integer.valueOf(((Boolean)obj[2]).booleanValue() ? 1 : 0) : null);
                return rol;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerTalleres", e);
        }
    }

    public Integer registrar(TallerDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarTaller");
        query.registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionCorta", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_Descripcion", param.getDescripcion());
        query.setParameter("p_DescripcionCorta", param.getDescripcionCorta());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            if (result.intValue() > 0)
                return result;
            throw new RuntimeException("No se encontraron talleres");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarTaller", e);
        }
    }

    public Integer editar(TallerDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ModificarTaller");
        query.registerStoredProcedureParameter("p_IdTaller", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_DescripcionCorta", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Activo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdTaller", param.getIdTaller());
        query.setParameter("p_Descripcion", param.getDescripcion());
        query.setParameter("p_DescripcionCorta", param.getDescripcionCorta());
        query.setParameter("p_Activo", param.getActivo());
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ModificarTaller", e);
        }
    }

    public Integer eliminar(Integer idTaller) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_EliminarTaller");
        query.registerStoredProcedureParameter("p_IdTaller", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_IdTaller", idTaller);
        try {
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_EliminarTaller", e);
        }
    }

    public List<EtapaDetalleTallerDto> listarAsignados() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarTalleresAsignados");
        try {
            List<Object[]> results = query.getResultList();
            List<EtapaDetalleTallerDto> talleres = (List<EtapaDetalleTallerDto>)results.stream().map(obj -> {
                EtapaDetalleTallerDto tallerDetalle = new EtapaDetalleTallerDto();
                tallerDetalle.setIdEtapaDetalleTaller((obj[0] != null) ? Integer.valueOf(((Integer)obj[0]).intValue()) : null);
                EtapaDetalleDto oEtapaDetalle = new EtapaDetalleDto();
                oEtapaDetalle.setIdEtapaDetalle((obj[1] != null) ? Integer.valueOf(((Integer)obj[1]).intValue()) : null);
                tallerDetalle.setEtapaDetalle(oEtapaDetalle);
                EtapaDto oEtapa = new EtapaDto();
                oEtapa.setIdEtapa((obj[2] != null) ? Integer.valueOf(((Integer)obj[2]).intValue()) : null);
                oEtapa.setDescripcionEtapa((String)obj[3]);
                oEtapa.setDescripcion((String)obj[4]);
                tallerDetalle.setOEtapa(oEtapa);
                GrupoDto oGrupo = new GrupoDto();
                oGrupo.setIdGrupo((obj[5] != null) ? Integer.valueOf(((Integer)obj[5]).intValue()) : null);
                oGrupo.setDescripcionGrupo((String)obj[6]);
                oGrupo.setDescripcion((String)obj[7]);
                tallerDetalle.setOGrupo(oGrupo);
                TallerDto oTaller = new TallerDto();
                oTaller.setIdTaller((obj[8] != null) ? Integer.valueOf(((Integer)obj[8]).intValue()) : null);
                oTaller.setDescripcion((String)obj[9]);
                oTaller.setActivo((obj[10] != null) ? Integer.valueOf(((Boolean)obj[10]).booleanValue() ? 1 : 0) : null);
                tallerDetalle.setOTaller(oTaller);
                return tallerDetalle;
            }).collect(Collectors.toList());
            return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ListarTalleresAsignados", e);
        }
    }

    public Integer asignarTaller(List<TallerDto> param) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonLista = mapper.writeValueAsString(param);
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_AsignarCursos");
            query.registerStoredProcedureParameter("p_Lista", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
            query.setParameter("p_Lista", jsonLista);
            query.execute();
            Integer result = (Integer)query.getOutputParameterValue("p_Resultado");
            return  result;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_AsignarCursos", e);
        }
    }

    public Integer registrarAperturaTaller(AperturaTallerDto param) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonLista = mapper.writeValueAsString(param);
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarAperturaTaller");
            query.registerStoredProcedureParameter("p_Json", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
            query.setParameter("p_Json", jsonLista);
            query.execute();
            return (Integer)query.getOutputParameterValue("p_Resultado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarAperturaTaller", e);
        }
    }

    public Integer actualizarAperturaTaller(AperturaTallerDto param) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonLista = mapper.writeValueAsString(param);
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ActualizarAperturaTaller");
            query.registerStoredProcedureParameter("p_Json", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
            query.setParameter("p_Json", jsonLista);
            query.execute();
            return (Integer)query.getOutputParameterValue("p_Resultado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ActualizarAperturaTaller", e);
        }
    }

    public List<AperturaTallerDto> listarAperturaTaller() {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ListarAperturaTalleres");
        Object result = query.getSingleResult();
        if (result == null)
            return List.of();
        String json = result.toString();
        try {
            return Arrays.asList((AperturaTallerDto[])this.objectMapper.readValue(json, AperturaTallerDto[].class));
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a usp_ListarAperturaTalleres", e);
        }
    }

    public Integer registrarMatricula(MatriculaDto param) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonLista = mapper.writeValueAsString(param);
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_RegistrarMatricula");
            query.registerStoredProcedureParameter("p_idAlumno", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_idAperturaTaller", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_idApoderado", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_tipo", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_pago", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_situacion", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
            query.setParameter("p_idAlumno", param.getIdAlumno());
            query.setParameter("p_idAperturaTaller", param.getIdAperturaTaller());
            query.setParameter("p_idApoderado", param.getIdApoderado());
            query.setParameter("p_tipo", param.getTipo());
            query.setParameter("p_pago", param.getPago());
            query.setParameter("p_situacion", param.getSituacion());
            query.execute();
            return (Integer)query.getOutputParameterValue("p_Resultado");
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_RegistrarMatricula", e);
        }
    }


    public List<CalendarioTallerDto> listarCalendarioTaller(Integer edad, Integer idAlumno) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerCalendarioTalleresJson");
        query.registerStoredProcedureParameter("p_edad", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_idAlumno", Integer.class, ParameterMode.IN);
        query.setParameter("p_edad", edad);
        query.setParameter("p_idAlumno", idAlumno);
        List<CalendarioTallerDto> result = query.getResultList();
        if (result == null)
            return List.of();
         try {
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a usp_ObtenerCalendarioTalleresJson", e);
        }
    }

    public List<TallerDocenteDto> listarTalleresConDocente(Integer idDocente) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ObtenerTalleresConDocente");
        query.registerStoredProcedureParameter("p_idDocente", Integer.class, ParameterMode.IN);
        query.setParameter("p_idDocente", idDocente);
        try {

        List<Object[]> results = query.getResultList();
        List<TallerDocenteDto> talleres = (List<TallerDocenteDto>)results.stream().map(obj -> {
            TallerDocenteDto tallerDetalle = new TallerDocenteDto();
            tallerDetalle.setIdAperturaTaller((obj[0] != null) ? Integer.valueOf(((Integer) obj[0]).intValue()) : null);
            tallerDetalle.setNombreTaller((obj[1] != null) ? String.valueOf(obj[1]) : null);
            tallerDetalle.setIdDocente((obj[2] != null) ? Integer.valueOf(((Integer) obj[2]).intValue()) : null);
            tallerDetalle.setCodigoDocente((obj[3] != null) ? String.valueOf(obj[3]) : null);
            tallerDetalle.setNombreDocente((obj[4] != null) ? String.valueOf(obj[4]) : null);
            tallerDetalle.setDescripcionEtapa((obj[5] != null) ? String.valueOf(obj[5]) : null);
            tallerDetalle.setDescripcionGrupo((obj[6] != null) ? String.valueOf(obj[6]) : null);
            tallerDetalle.setDescripcionTaller((obj[7] != null) ? String.valueOf(obj[7]) : null);

             return tallerDetalle;
        }).collect(Collectors.toList());
        return talleres;
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar usp_ObtenerTalleresConDocente", e);
        }
    }

    public Integer asignarDocenteTaller(TallerDocenteDto param) {
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_ActualizarDocenteTaller");
        query.registerStoredProcedureParameter("p_idDocente", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_idAperturaTaller", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_Resultado", Integer.class, ParameterMode.OUT);
        query.setParameter("p_idDocente", param.getIdDocente());
        query.setParameter("p_idAperturaTaller", param.getIdAperturaTaller());


        try {
            query.execute();
            return (Integer)query.getOutputParameterValue("p_Resultado");
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a usp_ActualizarDocenteTaller", e);
        }
    }




}
