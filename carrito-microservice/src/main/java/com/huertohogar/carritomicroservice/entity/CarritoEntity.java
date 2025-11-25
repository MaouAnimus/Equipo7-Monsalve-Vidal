package com.huertohogar.carritomicroservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class CarritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @OneToMany(mappedBy = "carrito", orphanRemoval = true)
    private List<DetalleCarritoEntity> detalles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<DetalleCarritoEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarritoEntity> detalles) {
        this.detalles = detalles;
    }
}
