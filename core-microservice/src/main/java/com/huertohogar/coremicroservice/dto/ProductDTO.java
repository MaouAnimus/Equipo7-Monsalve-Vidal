package com.huertohogar.coremicroservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String categoria;
}
