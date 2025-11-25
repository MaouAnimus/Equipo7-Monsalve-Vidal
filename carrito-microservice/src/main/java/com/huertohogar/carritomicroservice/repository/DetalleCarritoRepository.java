package com.huertohogar.carritomicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;

public interface DetalleCarritoRepository extends JpaRepository<DetalleCarritoEntity, Long> {
}
