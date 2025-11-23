package com.huertohogar.carritomicroservice.service;

import java.util.List;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;

public interface CarritoService {
    List<CarritoEntity> listarCarritos();
    CarritoEntity obtenerCarritoPorId(Long id);
    CarritoEntity crearCarrito(CarritoEntity carrito);
    void eliminarCarrito(Long id);
    CarritoEntity agregarProducto(Long carritoId, DetalleCarritoEntity detalle);
    CarritoEntity actualizarCantidad(Long carritoId, Long detalleId, Integer cantidad);
    CarritoEntity eliminarProducto(Long carritoId, Long detalleId);
}
