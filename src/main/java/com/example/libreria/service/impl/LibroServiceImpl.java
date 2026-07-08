package com.example.libreria.service.impl;

import com.example.libreria.dto.LibroDTO;
import com.example.libreria.model.Categoria;
import com.example.libreria.model.Libro;
import com.example.libreria.repository.CategoriaRepository;
import com.example.libreria.repository.LibroRepository;
import com.example.libreria.service.LibroService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    public LibroServiceImpl(LibroRepository libroRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<LibroDTO> findAll() {
        return libroRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO findById(Long id) {
        return toDTO(libroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con id: " + id)));
    }

    @Override
    public LibroDTO save(LibroDTO libroDTO) {

        Optional<Libro> isExist = libroRepository.findByIsbn(libroDTO.getIsbn());

        if(isExist.isPresent()) {
            throw new EntityExistsException("El ISBN de libro ya existe");
        }

        Libro libro = toEntity(libroDTO);
        return toDTO(libroRepository.save(libro));
    }

    @Override
    public LibroDTO update(Long id, LibroDTO libroDTO) {
        Libro existente = libroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con id: " + id));
        existente.setTitulo(libroDTO.getTitulo());
        existente.setIsbn(libroDTO.getIsbn());
        existente.setDisponible(libroDTO.isDisponible());
        if (libroDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(libroDTO.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + libroDTO.getCategoriaId()));
            existente.setCategoria(categoria);
        }
        return toDTO(libroRepository.save(existente));
    }

    @Override
    public void deleteById(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new EntityNotFoundException("Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }

    private LibroDTO toDTO(Libro libro) {
        Long categoriaId = libro.getCategoria() != null ? libro.getCategoria().getId() : null;
        return new LibroDTO(libro.getId(), libro.getTitulo(), libro.getIsbn(), libro.isDisponible(), categoriaId);
    }

    private Libro toEntity(LibroDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + dto.getCategoriaId()));
        return new Libro(dto.getId(), dto.getTitulo(), dto.getIsbn(), dto.isDisponible(), categoria);
    }
}
