package com.huertohogar.carritomicroservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huertohogar.carritomicroservice.dto.CarritoDTO;
import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.repository.CarritoRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    private CarritoDTO convertirADTO(CarritoEntity entity) {
        return new CarritoDTO(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getIdProducto(),
                entity.getCantidad(),
                entity.getTotal()
        );
    }

    private CarritoEntity convertirAEntity(CarritoDTO dto) {
        return new CarritoEntity(
                dto.getId(),
                dto.getIdUsuario(),
                dto.getIdProducto(),
                dto.getCantidad(),
                dto.getTotal()
        );
    }

    @Override
    public List<CarritoDTO> listarCarritos() {
        return carritoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarritoDTO obtenerCarritoPorId(Long id) {
        Optional<CarritoEntity> carrito = carritoRepository.findById(id);
        return carrito.map(this::convertirADTO).orElse(null);
    }

    @Override
    public CarritoDTO crearCarrito(CarritoDTO carritoDTO) {
        CarritoEntity entity = convertirAEntity(carritoDTO);
        CarritoEntity guardado = carritoRepository.save(entity);
        return convertirADTO(guardado);
    }

    @Override
    public CarritoDTO actualizarCarrito(Long id, CarritoDTO carritoDTO) {
        Optional<CarritoEntity> carritoExistente = carritoRepository.findById(id);
        if (carritoExistente.isPresent()) {
            CarritoEntity carrito = carritoExistente.get();
            carrito.setIdUsuario(carritoDTO.getIdUsuario());
            carrito.setIdProducto(carritoDTO.getIdProducto());
            carrito.setCantidad(carritoDTO.getCantidad());
            carrito.setTotal(carritoDTO.getTotal());
            CarritoEntity actualizado = carritoRepository.save(carrito);
            return convertirADTO(actualizado);
        }
        return null;
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}
