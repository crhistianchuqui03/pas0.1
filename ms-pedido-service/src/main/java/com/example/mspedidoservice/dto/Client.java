package com.example.mspedidoservice.dto;

import lombok.Data;

@Data
public class Client {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String genero;
    private String nacionalidad;
}
