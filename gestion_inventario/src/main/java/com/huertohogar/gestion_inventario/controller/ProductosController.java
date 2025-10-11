package com.huertohogar.gestion_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huertohogar.gestion_inventario.model.Productos;
import com.huertohogar.gestion_inventario.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
	@Autowired
	private ProductoService prodService;
	
	@GetMapping
    public List<Productos> listar() {
        return prodService.listar();
    }

    @PostMapping
    public Productos guardar(@RequestBody Productos producto) {
        return prodService.guardarProd(producto);
    }

    @GetMapping("/{id}")
    public Productos obtener(@PathVariable Long id) {
        return prodService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
    	prodService.deleteById(id);
    }
}
