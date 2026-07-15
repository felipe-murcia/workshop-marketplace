package com.example.libreria.service;

import com.example.libreria.model.Libro;
import com.example.libreria.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public List<Libro> findAll() {
        return repository.findAll();
    }

    public Optional<Libro> findById(Long id) {
        return repository.findById(id);
    }

    public Libro save(Libro libro) {
        return repository.save(libro);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}