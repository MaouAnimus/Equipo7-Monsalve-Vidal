package com.huertohogar.coremicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.coremicroservice.entity.ProductEntity;
import com.huertohogar.coremicroservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

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
    public ProductEntity actualizarProducto(Long id, ProductEntity productoDetalles) {
        ProductEntity producto = productRepository.findById(id).orElse(null);
        if (producto == null) return null;

        if (productoDetalles.getNombre() != null)
            producto.setNombre(productoDetalles.getNombre());
        if (productoDetalles.getPrecio() != null)
            producto.setPrecio(productoDetalles.getPrecio());
        if (productoDetalles.getStock() != null)
            producto.setStock(productoDetalles.getStock());
        if (productoDetalles.getCategoria() != null)
            producto.setCategoria(productoDetalles.getCategoria());

        return productRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }
}
