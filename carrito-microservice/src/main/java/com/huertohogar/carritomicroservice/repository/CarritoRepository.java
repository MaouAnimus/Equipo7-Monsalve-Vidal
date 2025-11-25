package com.huertohogar.carritomicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.huertohogar.carritomicroservice.entity.CarritoEntity;

public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {
}
