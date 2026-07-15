package com.example.libreria.service;

import com.example.libreria.model.Categoria;
import com.example.libreria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}