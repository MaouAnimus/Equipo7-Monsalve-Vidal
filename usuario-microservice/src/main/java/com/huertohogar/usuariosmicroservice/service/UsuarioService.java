package com.huertohogar.usuariosmicroservice.service;

import java.util.List;
import com.huertohogar.usuariosmicroservice.entity.UsuarioEntity;

public interface UsuarioService {

    List<UsuarioEntity> listarUsuarios();

    UsuarioEntity buscarUsuarioPorId(Long id);

    UsuarioEntity crearUsuario(UsuarioEntity usuario);

    UsuarioEntity actualizarUsuario(Long id, UsuarioEntity usuario);

    void eliminarUsuario(Long id);
}
