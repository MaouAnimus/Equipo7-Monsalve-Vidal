package com.huertohogar.usuariosmicroservice.service;

import java.util.List;
import com.huertohogar.usuariosmicroservice.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> listarProductos();
    ProductDTO obtenerProductoPorId(Long id);
    ProductDTO crearProducto(ProductDTO producto);
    ProductDTO actualizarProducto(Long id, ProductDTO producto);
    void eliminarProducto(Long id);
}
