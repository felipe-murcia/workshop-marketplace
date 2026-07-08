package com.example.libreria.service;

import com.example.libreria.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDTO> findAll();

    CategoriaDTO findById(Long id);

    CategoriaDTO save(CategoriaDTO categoriaDTO);

    CategoriaDTO update(Long id, CategoriaDTO categoriaDTO);

    void deleteById(Long id);
}
