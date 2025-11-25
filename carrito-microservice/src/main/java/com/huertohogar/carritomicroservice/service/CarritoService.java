package com.huertohogar.carritomicroservice.service;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;
import java.util.List;

public interface CarritoService {

    CarritoEntity crearCarrito(CarritoEntity carrito);

    CarritoEntity obtenerCarrito(Long id);

    CarritoEntity agregarProducto(Long carritoId, DetalleCarritoEntity detalle);

    CarritoEntity actualizarCantidad(Long carritoId, Long detalleId, Integer cantidad);

    CarritoEntity eliminarProducto(Long carritoId, Long detalleId);
    List<CarritoEntity> listarCarritos();

}
