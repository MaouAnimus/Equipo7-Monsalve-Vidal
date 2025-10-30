package com.huertohogar.coremicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.huertohogar.coremicroservice.entity.ProductEntity;
import com.huertohogar.coremicroservice.service.ProductService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listarProductos() {
        return ResponseEntity.ok(productService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> obtenerProducto(@PathVariable("id") Long id) {
        ProductEntity producto = productService.obtenerProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> crearProducto(@RequestBody ProductEntity producto) {
        ProductEntity nuevoProducto = productService.crearProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductEntity producto) {
        ProductEntity actualizado = productService.actualizarProducto(id, producto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
