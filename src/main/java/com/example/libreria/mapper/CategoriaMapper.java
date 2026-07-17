package com.example.libreria.mapper;

import com.example.libreria.dto.CategoriaRequest;
import com.example.libreria.dto.CategoriaResponse;
import com.example.libreria.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNombre());
    }

    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        return categoria;
    }
}