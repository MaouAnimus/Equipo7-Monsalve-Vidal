package com.huertohogar.gestion_usuario.service;

import com.huertohogar.gestion_usuario.model.UsuarioModel;
import com.huertohogar.gestion_usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioModel updateUsuario(Long id, UsuarioModel usuarioDetails) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setFirstName(usuarioDetails.getFirstName());
        usuario.setLastName(usuarioDetails.getLastName());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setUsername(usuarioDetails.getUsername());
        usuario.setPassword(usuarioDetails.getPassword());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }
}
