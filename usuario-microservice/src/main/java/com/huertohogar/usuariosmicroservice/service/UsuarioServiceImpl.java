package com.huertohogar.usuariosmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.usuariosmicroservice.entity.UsuarioEntity;
import com.huertohogar.usuariosmicroservice.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity buscarUsuarioPorId(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    @Override
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity actualizarUsuario(Long id, UsuarioEntity usuarioDetalles) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

        if (usuarioDetalles.getNombre() != null)
            usuario.setNombre(usuarioDetalles.getNombre());
        if (usuarioDetalles.getEmail() != null)
            usuario.setEmail(usuarioDetalles.getEmail());
        if (usuarioDetalles.getPassword() != null)
            usuario.setPassword(usuarioDetalles.getPassword());
        if (usuarioDetalles.getRol() != null)
            usuario.setRol(usuarioDetalles.getRol());

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
