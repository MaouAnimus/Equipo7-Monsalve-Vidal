package com.huertohogar.carritomicroservice.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.huertohogar.carritomicroservice.entity.CarritoEntity;
import com.huertohogar.carritomicroservice.entity.DetalleCarritoEntity;
import com.huertohogar.carritomicroservice.service.CarritoService;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private static final Logger logger = LoggerFactory.getLogger(CarritoController.class);

    private final CarritoService carritoService;
    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public CarritoController(CarritoService carritoService, SqsClient sqsClient) {
        this.carritoService = carritoService;
        this.sqsClient = sqsClient;
    }

    @GetMapping
    public List<CarritoEntity> listar() {
        return carritoService.listarCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoEntity> obtener(@PathVariable Long id) {
        CarritoEntity carrito = carritoService.obtenerCarritoPorId(id);
        if (carrito == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<CarritoEntity> crear(@RequestBody CarritoEntity carrito) {
        CarritoEntity creado = carritoService.crearCarrito(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PostMapping("/agregar/{carritoId}")
    public ResponseEntity<CarritoEntity> agregarProducto(
            @PathVariable Long carritoId,
            @RequestBody DetalleCarritoEntity detalle) {
        try {
            CarritoEntity updated = carritoService.agregarProducto(carritoId, detalle);
            if (updated == null) return ResponseEntity.notFound().build();

            enviarMensajeSqs("Producto agregado: " + detalle.getProductoId() + " cantidad: " + detalle.getCantidad());
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            logger.error("Error agregando producto al carrito {}", carritoId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{carritoId}/detalle/{detalleId}")
    public ResponseEntity<CarritoEntity> actualizarCantidad(
            @PathVariable Long carritoId,
            @PathVariable Long detalleId,
            @RequestBody DetalleCarritoEntity detalle) { // ahora usa RequestBody
        try {
            CarritoEntity updated = carritoService.actualizarCantidad(carritoId, detalleId, detalle.getCantidad());
            if (updated == null) return ResponseEntity.notFound().build();

            enviarMensajeSqs("Cantidad actualizada: detalle " + detalleId + " nueva cantidad: " + detalle.getCantidad());
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            logger.error("Error actualizando cantidad en carrito {}", carritoId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{carritoId}/detalle/{detalleId}")
    public ResponseEntity<CarritoEntity> eliminarDetalle(
            @PathVariable Long carritoId,
            @PathVariable Long detalleId) {
        try {
            CarritoEntity updated = carritoService.eliminarProducto(carritoId, detalleId);
            if (updated == null) return ResponseEntity.notFound().build();

            enviarMensajeSqs("Producto eliminado: detalle " + detalleId);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            logger.error("Error eliminando detalle {} del carrito {}", detalleId, carritoId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void enviarMensajeSqs(String mensaje) {
        try {
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(mensaje)
                    .build();
            sqsClient.sendMessage(request);
            logger.info("Mensaje enviado a SQS: {}", mensaje);
        } catch (Exception e) {
            logger.error("Error enviando mensaje a SQS", e);
        }
    }
}
