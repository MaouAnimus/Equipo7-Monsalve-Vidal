package com.huertohogar.carritomicroservice.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    private Long id;
    private Long usuarioId;
    private String estado;
    private List<DetalleCarritoDTO> detalles;
}
