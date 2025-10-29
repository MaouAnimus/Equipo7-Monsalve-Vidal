package com.huertohogar.carritomicroservice.service;

import java.util.List;
import com.huertohogar.carritomicroservice.dto.CarritoDTO;

public interface CarritoService {
    List<CarritoDTO> listarCarritos();
    CarritoDTO obtenerCarritoPorId(Long id);
    CarritoDTO crearCarrito(CarritoDTO carrito);
    CarritoDTO actualizarCarrito(Long id, CarritoDTO carrito);
    void eliminarCarrito(Long id);
}
