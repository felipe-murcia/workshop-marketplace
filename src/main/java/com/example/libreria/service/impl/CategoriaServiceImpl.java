package com.example.libreria.service.impl;

import com.example.libreria.dto.CategoriaDTO;
import com.example.libreria.model.Categoria;
import com.example.libreria.repository.CategoriaRepository;
import com.example.libreria.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + id));
        System.out.println("Categoria encontrada con id: " + id);
        return toDTO(categoria);
    }

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = toEntity(categoriaDTO);
        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con id: " + id));
        existente.setNombre(categoriaDTO.getNombre());
        return toDTO(categoriaRepository.save(existente));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }

    private Categoria toEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        return categoria;
    }
}
