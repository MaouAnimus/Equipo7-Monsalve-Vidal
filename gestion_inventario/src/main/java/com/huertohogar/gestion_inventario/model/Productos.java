package com.huertohogar.gestion_inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private int precio;

    @Column
    private int stock;

    @Column(length = 255) 
    private String imagen;

    @Column(length = 100, nullable = true)
    private Categoria categoria;

    @Column(length = 100, nullable = true)
    private String subcategoria;
}
