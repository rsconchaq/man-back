package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExternalSunatDto {
               private String razon_social ;
               private String numero_documento ;
               private String estado ;
               private String condicion ;
               private String direccion ;
               private String ubigeo ;
               private String via_tipo ;
               private String via_nombre ;
               private String zona_codigo ;
               private String zona_tipo ;
               private String numero ;
               private String interior ;
               private String lote ;
               private String dpto ;
               private String manzana ;
               private String kilometro ;
               private String distrito ;
               private String provincia ;
               private String departamento ;
               private String es_agente_retencion ;
               private String es_buen_contribuyente ;
               private List<ExternalSunatLocales> locales_anexos ;
}
