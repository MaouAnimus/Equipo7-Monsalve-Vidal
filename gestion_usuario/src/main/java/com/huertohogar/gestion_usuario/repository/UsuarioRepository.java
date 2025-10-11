package com.huertohogar.gestion_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huertohogar.gestion_usuario.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long>{
	
}
