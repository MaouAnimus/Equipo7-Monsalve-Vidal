package com.huertohogar.gestion_inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.gestion_inventario.model.Productos;
import com.huertohogar.gestion_inventario.repository.ProductosRepository;


@Service
public class ProductoService {
	
	@Autowired
	private ProductosRepository productrepo;

	public List<Productos> listar() {
        return productrepo.findAll();
    }
	
	public Productos guardarProd(Productos producto) {
		return productrepo.save(producto);
	}
	
	public Productos findById(Long id) {
        return productrepo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
    	Productos producto = productrepo.findById(id).orElseThrow(() 
    			-> new RuntimeException("Producto no encontrado con id: " + id));
    	productrepo.delete(producto);
    }
}
