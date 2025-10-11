package com.huertohogar.gestion_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huertohogar.gestion_inventario.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long>{

}
