package com.example.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.sql.Update;
import org.springframework.data.annotation.CreatedBy;

public class LibroDTO {

    private Long id;
    @NotBlank(message = "El campo de titulo es obligatorio")
    private String titulo;
    @Size(min = 5, max = 15, message = "")
    private String isbn;
    private boolean disponible;
    @NotNull(message = "El campo de categoriaId es obligatorio")
    @NotNull(groups = { Update.class })
    private Long categoriaId;

    public LibroDTO() {
    }

    public LibroDTO(String titulo, String isbn, boolean disponible, Long categoriaId) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.categoriaId = categoriaId;
    }

    public LibroDTO(Long id, String titulo, String isbn, boolean disponible, Long categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
