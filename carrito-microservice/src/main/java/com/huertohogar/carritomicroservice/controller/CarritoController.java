package com.huertohogar.carritomicroservice.controller;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;
import com.huertohogar.carritomicroservice.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarritoEntity> crear(@RequestBody CarritoEntity carrito) {
        return ResponseEntity.ok(service.crearCarrito(carrito));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoEntity> obtener(@PathVariable Long id) {
        CarritoEntity carrito = service.obtenerCarrito(id);
        if (carrito == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/{id}/agregar")
    public ResponseEntity<CarritoEntity> agregarProducto(@PathVariable Long id, @RequestBody DetalleCarritoEntity detalle) {
        CarritoEntity carrito = service.agregarProducto(id, detalle);
        if (carrito == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrito);
    }
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.listarCarritos());
    }

    @PutMapping("/{id}/detalle/{detalleId}")
    public ResponseEntity<CarritoEntity> actualizarCantidad(
            @PathVariable Long id,
            @PathVariable Long detalleId,
            @RequestBody DetalleCarritoEntity detalle) {

        CarritoEntity carrito = service.actualizarCantidad(id, detalleId, detalle.getCantidad());
        if (carrito == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping("/{id}/detalle/{detalleId}")
    public ResponseEntity<CarritoEntity> eliminarDetalle(@PathVariable Long id, @PathVariable Long detalleId) {
        CarritoEntity carrito = service.eliminarProducto(id, detalleId);
        if (carrito == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrito);
    }
}
