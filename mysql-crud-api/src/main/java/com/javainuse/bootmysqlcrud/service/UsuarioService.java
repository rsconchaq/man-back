package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.UsuarioDetalleDto;
import com.javainuse.bootmysqlcrud.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    int loginUsuario(String paramString1, String paramString2);

    UsuarioDetalleDto obtenerDetalleUsuario(Integer paramInteger);

    UsuarioDto obtenerUsuarioXName(String paramString);

    List<UsuarioDto> listar();

    Integer registrar(UsuarioDto paramUsuarioDto);

    Integer editar(UsuarioDto paramUsuarioDto);

    Integer eliminar(Integer paramInteger);

    Integer actualizar_validar(String paramString1, String paramString2, String paramString3);
}
