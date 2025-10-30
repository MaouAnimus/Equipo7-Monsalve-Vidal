package com.huertohogar.carritomicroservice.service;

import java.util.List;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;

public interface CarritoService {
    List<CarritoEntity> listarCarritos();
    CarritoEntity obtenerCarritoPorId(Long id);
    CarritoEntity crearCarrito(CarritoEntity carrito);
    CarritoEntity actualizarCarrito(Long id, CarritoEntity carrito);
    void eliminarCarrito(Long id);
}
