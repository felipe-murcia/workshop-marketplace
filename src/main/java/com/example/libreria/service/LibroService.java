package com.example.libreria.service;

import com.example.libreria.dto.LibroDTO;

import java.util.List;

public interface LibroService {

    List<LibroDTO> findAll();

    LibroDTO findById(Long id);

    LibroDTO save(LibroDTO libroDTO);

    LibroDTO update(Long id, LibroDTO libroDTO);

    void deleteById(Long id);
}
