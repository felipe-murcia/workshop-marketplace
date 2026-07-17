package com.example.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequest {

    @NotBlank
    @Size(min = 4, max = 100, message = "El nombre debe tener entre 5 y 50 caracteres")
    private String nombre;

    public CategoriaRequest() {
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
