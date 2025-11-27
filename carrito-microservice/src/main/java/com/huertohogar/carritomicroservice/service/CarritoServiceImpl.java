package com.huertohogar.carritomicroservice.service;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;
import com.huertohogar.carritomicroservice.repository.CarritoRepository;
import com.huertohogar.carritomicroservice.repository.DetalleCarritoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final DetalleCarritoRepository detalleRepository;

    public CarritoServiceImpl(CarritoRepository carritoRepository,
                              DetalleCarritoRepository detalleRepository) {
        this.carritoRepository = carritoRepository;
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<CarritoEntity> listarCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public CarritoEntity crearCarrito(CarritoEntity carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public CarritoEntity obtenerCarrito(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    @Override
    public CarritoEntity agregarProducto(Long carritoId, DetalleCarritoEntity detalle) {
        Optional<CarritoEntity> carritoOpt = carritoRepository.findById(carritoId);
        if (carritoOpt.isEmpty()) return null;

        CarritoEntity carrito = carritoOpt.get();
        detalle.setCarrito(carrito);
        detalleRepository.save(detalle);

        return carritoRepository.findById(carritoId).orElse(null);
    }

    @Override
    public CarritoEntity actualizarCantidad(Long carritoId, Long detalleId, Integer cantidad) {
        Optional<DetalleCarritoEntity> detalleOpt = detalleRepository.findById(detalleId);
        if (detalleOpt.isEmpty()) return null;

        DetalleCarritoEntity detalle = detalleOpt.get();
        detalle.setCantidad(cantidad);
        detalleRepository.save(detalle);

        return carritoRepository.findById(carritoId).orElse(null);
    }

    @Override
    public CarritoEntity eliminarProducto(Long carritoId, Long detalleId) {
        if (!detalleRepository.existsById(detalleId)) return null;

        detalleRepository.deleteById(detalleId);

        return carritoRepository.findById(carritoId).orElse(null);
    }
}
