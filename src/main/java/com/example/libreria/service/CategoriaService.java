package com.example.libreria.service;

import com.example.libreria.dto.CategoriaRequest;
import com.example.libreria.dto.CategoriaResponse;
import com.example.libreria.exception.DuplicateResourceException;
import com.example.libreria.mapper.CategoriaMapper;
import com.example.libreria.model.Categoria;
import com.example.libreria.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoriaResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<CategoriaResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public CategoriaResponse save(CategoriaRequest request) {
        if (repository.existsByNombre(request.getNombre())) {
            throw new DuplicateResourceException("Ya existe una categoría con el nombre: " + request.getNombre());
        }
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public CategoriaResponse update(Long id, String nombre) {
        Categoria existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + id));
        existing.setNombre(nombre);
        return mapper.toResponse(repository.save(existing));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}