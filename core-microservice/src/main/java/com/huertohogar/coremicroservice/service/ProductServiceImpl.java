package com.huertohogar.coremicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.coremicroservice.entity.ProductEntity;
import com.huertohogar.coremicroservice.repository.ProductRepository;
import com.huertohogar.coremicroservice.sqs.StockPublisher;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private StockPublisher stockPublisher;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> listarProductos() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity obtenerProductoPorId(Long id) {
        Optional<ProductEntity> producto = productRepository.findById(id);
        return producto.orElse(null);
    }

    @Override
    public ProductEntity crearProducto(ProductEntity producto) {
        return productRepository.save(producto);
    }

    @Override
    public ProductEntity actualizarProducto(Long id, ProductEntity detalles) {
        ProductEntity producto = productRepository.findById(id).orElse(null);
        if (producto == null) return null;


        if (detalles.getStock() != null && !detalles.getStock().equals(producto.getStock())) {
            int newStock = detalles.getStock();
            producto.setStock(newStock);
            stockPublisher.publicarCambioStock(id, newStock);
        }

        if (detalles.getNombre() != null) producto.setNombre(detalles.getNombre());
        if (detalles.getPrecio() != null) producto.setPrecio(detalles.getPrecio());
        if (detalles.getCategoria() != null) producto.setCategoria(detalles.getCategoria());

        return productRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }
}
