package com.example.mspedidoservice.service.impl;


import com.example.mspedidoservice.dto.Client;
import com.example.mspedidoservice.dto.Producto;
import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feign.ClientFeign;
import com.example.mspedidoservice.feign.ProductoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRespository;
    @Autowired
    private ClientFeign clientFeign;
    @Autowired
    private ProductoFeign productoFeign;

    @Override
    public List<Pedido> listar() {
        return pedidoRespository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Integer id) {
        Pedido pedido = pedidoRespository.findById(id).get();
        pedido.setClient(clientFeign.listById(pedido.getClientId()).getBody());
        for (PedidoDetalle pedidoDetalle: pedido.getDetalle()){
            pedidoDetalle.setProducto(productoFeign.listById(pedidoDetalle.getProductoId()).getBody());

        }
        return Optional.of(pedido);
    }


    @Override
    public Pedido editar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRespository.deleteById(id);
    }
}


