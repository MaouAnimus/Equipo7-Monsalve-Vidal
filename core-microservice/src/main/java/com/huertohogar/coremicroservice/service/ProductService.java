package com.huertohogar.coremicroservice.service;

import java.util.List;

import com.huertohogar.coremicroservice.entity.ProductEntity;

public interface ProductService {
    List<ProductEntity> listarProductos();
    ProductEntity obtenerProductoPorId(Long id);
    ProductEntity crearProducto(ProductEntity producto);
    ProductEntity actualizarProducto(Long id, ProductEntity producto);
    void eliminarProducto(Long id);
}
