package com.huertohogar.usuariosmicroservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.usuariosmicroservice.dto.ProductDTO;
import com.huertohogar.usuariosmicroservice.entity.ProductEntity;
import com.huertohogar.usuariosmicroservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductDTO convertirADTO(ProductEntity entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getCategoria()
        );
    }

    private ProductEntity convertirAEntity(ProductDTO dto) {
        return new ProductEntity(
                dto.getId(),
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria()
        );
    }

    @Override
    public List<ProductDTO> listarProductos() {
        return productRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO obtenerProductoPorId(Long id) {
        Optional<ProductEntity> producto = productRepository.findById(id);
        return producto.map(this::convertirADTO).orElse(null);
    }

    @Override
    public ProductDTO crearProducto(ProductDTO productoDTO) {
        ProductEntity entity = convertirAEntity(productoDTO);
        ProductEntity guardado = productRepository.save(entity);
        return convertirADTO(guardado);
    }

    @Override
    public ProductDTO actualizarProducto(Long id, ProductDTO productoDTO) {
        Optional<ProductEntity> productoExistente = productRepository.findById(id);
        if (productoExistente.isPresent()) {
            ProductEntity producto = productoExistente.get();
            producto.setNombre(productoDTO.getNombre());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setStock(productoDTO.getStock());
            producto.setCategoria(productoDTO.getCategoria());
            ProductEntity actualizado = productRepository.save(producto);
            return convertirADTO(actualizado);
        }
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }
}
