package com.huertohogar.carritomicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.repository.CarritoRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<CarritoEntity> listarCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public CarritoEntity obtenerCarritoPorId(Long id) {
        Optional<CarritoEntity> carrito = carritoRepository.findById(id);
        return carrito.orElse(null);
    }

    @Override
    public CarritoEntity crearCarrito(CarritoEntity carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public CarritoEntity actualizarCarrito(Long id, CarritoEntity carritoDetalles) {
        CarritoEntity carrito = carritoRepository.findById(id).orElse(null);
        if (carrito == null) return null;

        if (carritoDetalles.getIdUsuario() != null)
            carrito.setIdUsuario(carritoDetalles.getIdUsuario());
        if (carritoDetalles.getIdProducto() != null)
            carrito.setIdProducto(carritoDetalles.getIdProducto());
        if (carritoDetalles.getCantidad() != null)
            carrito.setCantidad(carritoDetalles.getCantidad());
        if (carritoDetalles.getTotal() != null)
            carrito.setTotal(carritoDetalles.getTotal());

        return carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}
