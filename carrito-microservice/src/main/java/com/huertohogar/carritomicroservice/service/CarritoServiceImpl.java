package com.huertohogar.carritomicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final List<CarritoEntity> carritos = new ArrayList<>();
    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    private static long carritoIdCounter = 1;
    private static long detalleIdCounter = 1;

    public CarritoServiceImpl(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public List<CarritoEntity> listarCarritos() {
        return carritos;
    }

    @Override
    public CarritoEntity obtenerCarritoPorId(Long id) {
        return carritos.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public CarritoEntity crearCarrito(CarritoEntity carrito) {
        if (carrito.getId() == null) carrito.setId(carritoIdCounter++);
        if (carrito.getDetalles() == null) carrito.setDetalles(new ArrayList<>());
        carritos.add(carrito);
        return carrito;
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritos.removeIf(c -> Objects.equals(c.getId(), id));
    }

    @Override
    public CarritoEntity agregarProducto(Long carritoId, DetalleCarritoEntity detalle) {
        CarritoEntity carrito = obtenerCarritoPorId(carritoId);
        if (carrito != null) {
            if (detalle.getId() == null) detalle.setId(detalleIdCounter++);
            detalle.setCarrito(carrito);
            carrito.getDetalles().add(detalle);

            enviarMensaje("Producto agregado: " + detalle.getProductoId() + " cantidad: " + detalle.getCantidad());
        }
        return carrito;
    }

    @Override
    public CarritoEntity actualizarCantidad(Long carritoId, Long detalleId, Integer cantidad) {
        CarritoEntity carrito = obtenerCarritoPorId(carritoId);
        if (carrito != null) {
            Optional<DetalleCarritoEntity> detalleOpt = carrito.getDetalles().stream()
                    .filter(d -> Objects.equals(d.getId(), detalleId))
                    .findFirst();
            if (detalleOpt.isPresent()) {
                detalleOpt.get().setCantidad(cantidad);
                enviarMensaje("Cantidad actualizada: detalle " + detalleId + " nueva cantidad: " + cantidad);
            }
        }
        return carrito;
    }

    @Override
    public CarritoEntity eliminarProducto(Long carritoId, Long detalleId) {
        CarritoEntity carrito = obtenerCarritoPorId(carritoId);
        if (carrito != null) {
            boolean removed = carrito.getDetalles().removeIf(d -> Objects.equals(d.getId(), detalleId));
            if (removed) enviarMensaje("Producto eliminado: detalle " + detalleId);
        }
        return carrito;
    }

    private void enviarMensaje(String mensaje) {
        try {
            sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(mensaje)
                    .build());
            System.out.println("Mensaje enviado a SQS: " + mensaje);
        } catch (Exception e) {
            System.out.println("Error enviando mensaje a SQS:");
            e.printStackTrace();
        }
    }
}
