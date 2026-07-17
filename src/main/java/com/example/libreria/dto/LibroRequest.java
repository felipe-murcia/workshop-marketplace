package com.example.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class LibroRequest {

    @NotBlank
    @Size(min = 4, max = 200, message = "El título debe tener entre 5 y 200 caracteres")
    private String titulo;

    @NotBlank
    private String autor;

    @NotNull
    @Positive
    private BigDecimal precio;

    @NotNull
    private Long categoriaId;

    public LibroRequest() {
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
