package com.huertohogar.usuariosmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.huertohogar.usuariosmicroservice.dto.ProductDTO;
import com.huertohogar.usuariosmicroservice.service.ProductService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listarProductos() {
        return ResponseEntity.ok(productService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable Long id) {
        ProductDTO producto = productService.obtenerProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> crearProducto(@RequestBody ProductDTO producto) {
        return ResponseEntity.ok(productService.crearProducto(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductDTO producto) {
        ProductDTO actualizado = productService.actualizarProducto(id, producto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
