package com.huertohogar.carritomicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.service.CarritoService;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<CarritoEntity>> listarCarritos() {
        return ResponseEntity.ok(carritoService.listarCarritos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoEntity> obtenerCarrito(@PathVariable("id") Long id) {
        CarritoEntity carrito = carritoService.obtenerCarritoPorId(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<CarritoEntity> crearCarrito(@RequestBody CarritoEntity carrito) {
        CarritoEntity nuevoCarrito = carritoService.crearCarrito(carrito);
        return ResponseEntity.ok(nuevoCarrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoEntity> actualizarCarrito(@PathVariable("id") Long id, @RequestBody CarritoEntity carrito) {
        CarritoEntity actualizado = carritoService.actualizarCarrito(id, carrito);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable("id") Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
