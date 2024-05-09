package com.example.mspedidoservice.feign;

import com.example.mspedidoservice.dto.Client;
import com.example.mspedidoservice.entity.Pedido;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-client-service",path = "/cliente")
public interface ClientFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListarPorIdCB", fallbackMethod = "fallbackclientePorId")
    public ResponseEntity<Client> listById(@PathVariable(required = true) Integer id);
    default ResponseEntity<Client> fallbackclientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new Client());
    }



}
