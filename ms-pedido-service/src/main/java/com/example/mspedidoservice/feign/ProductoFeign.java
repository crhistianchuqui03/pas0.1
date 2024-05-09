package com.example.mspedidoservice.feign;

import com.example.mspedidoservice.dto.Producto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo-service",path = "/producto")
public interface ProductoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productolistByIdCB", fallbackMethod = "fallbackProductoPorId")
    public ResponseEntity<Producto> listById(@PathVariable(required = true) Integer id);
    default ResponseEntity<Producto> fallbackProductoPorId(Integer id, Exception e) {
        return ResponseEntity.ok(new Producto());
    }


}
