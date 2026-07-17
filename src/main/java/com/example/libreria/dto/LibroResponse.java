package com.example.libreria.dto;

import java.math.BigDecimal;

public class LibroResponse {

    private Long id;
    private String titulo;
    private String autor;
    private BigDecimal precio;
    private CategoriaResponse categoria;

    public LibroResponse() {
    }

    public LibroResponse(Long id, String titulo, String autor, BigDecimal precio, CategoriaResponse categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public CategoriaResponse getCategoria() { return categoria; }
    public void setCategoria(CategoriaResponse categoria) { this.categoria = categoria; }
}
