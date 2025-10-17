package com.huertohogar.gestion_usuario.service;

import com.huertohogar.gestion_usuario.model.UsuarioModel;
import com.huertohogar.gestion_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel crearUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel actualizarUsuario(Long id, UsuarioModel nuevoUsuario) {
        return usuarioRepository.findById(id)
                .map(u -> {
                    u.setUsername(nuevoUsuario.getUsername());
                    u.setEmail(nuevoUsuario.getEmail());
                    u.setFirstName(nuevoUsuario.getFirstName());
                    u.setLastName(nuevoUsuario.getLastName());
                    return usuarioRepository.save(u);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioModel> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
