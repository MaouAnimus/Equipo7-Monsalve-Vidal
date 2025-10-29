package com.huertohogar.carritomicroservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private Integer cantidad;
    private Double total;
}
