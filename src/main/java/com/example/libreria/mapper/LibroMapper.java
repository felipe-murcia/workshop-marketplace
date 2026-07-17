package com.example.libreria.mapper;

import com.example.libreria.dto.CategoriaResponse;
import com.example.libreria.dto.LibroRequest;
import com.example.libreria.dto.LibroResponse;
import com.example.libreria.model.Categoria;
import com.example.libreria.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    public LibroResponse toResponse(Libro libro) {
        CategoriaResponse categoriaResponse = new CategoriaResponse(
                libro.getCategoria().getId(),
                libro.getCategoria().getNombre()
        );
        return new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor(),
                libro.getPrecio(), categoriaResponse);
    }

    public Libro toEntity(LibroRequest request, Categoria categoria) {
        Libro libro = new Libro();
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setPrecio(request.getPrecio());
        libro.setCategoria(categoria);
        return libro;
    }
}