package com.huertohogar.carritomicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.huertohogar.carritomicroservice.entity.CarritoEntity;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {
}
