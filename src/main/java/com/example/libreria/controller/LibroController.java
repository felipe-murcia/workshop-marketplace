package com.example.libreria.controller;

import com.example.libreria.dto.ErrorResponseDTO;
import com.example.libreria.dto.LibroDTO;
import com.example.libreria.service.LibroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDTO>> findAll() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LibroDTO> save(@Valid @RequestBody LibroDTO libroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.save(libroDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> update(@PathVariable Long id, @Valid @RequestBody LibroDTO libroDTO) {
        try {
            return ResponseEntity.ok(libroService.update(id, libroDTO));
        } catch (EntityNotFoundException e) {
            System.out.println("No existe el libro con el id: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            libroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
