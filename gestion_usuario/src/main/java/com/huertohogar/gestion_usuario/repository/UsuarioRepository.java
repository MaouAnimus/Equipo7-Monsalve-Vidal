package com.huertohogar.gestion_usuario.repository;

import com.huertohogar.gestion_usuario.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
