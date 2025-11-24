package com.huertohogar.carritomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarritoDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
}
