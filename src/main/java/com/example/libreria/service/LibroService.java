package com.example.libreria.service;

import com.example.libreria.dto.LibroRequest;
import com.example.libreria.dto.LibroResponse;
import com.example.libreria.mapper.LibroMapper;
import com.example.libreria.model.Categoria;
import com.example.libreria.model.Libro;
import com.example.libreria.repository.CategoriaRepository;
import com.example.libreria.repository.LibroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final LibroMapper mapper;

    public LibroService(LibroRepository repository, CategoriaRepository categoriaRepository, LibroMapper mapper) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    public List<LibroResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<LibroResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public LibroResponse save(LibroRequest request) {
        Long categoriaId = request.getCategoriaId();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + categoriaId));
        return mapper.toResponse(repository.save(mapper.toEntity(request, categoria)));
    }

    public LibroResponse update(Long id, LibroRequest request) {
        Libro existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con id: " + id));
        Long categoriaId = request.getCategoriaId();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + categoriaId));
        Libro updated = mapper.toEntity(request, categoria);
        updated.setId(existing.getId());
        return mapper.toResponse(repository.save(updated));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}