package com.example.libreria.service;

import com.example.libreria.dto.CategoriaDTO;
import com.example.libreria.exception.DuplicateResourceException;
import com.example.libreria.model.Categoria;
import com.example.libreria.repository.CategoriaRepository;
import com.example.libreria.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Test
    void obtenerTodosCategorias() {
        Mockito.when(categoriaRepository.findAll())
                .thenReturn(List.of(
                        new Categoria(1L, "Novela"),
                        new Categoria(2L, "Thriller")
                ));

        List<CategoriaDTO> result = categoriaService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        assertThat(result).hasSize(2);
    }

    @Test
    void consultarPorId() {
        Categoria categoria = new Categoria(1L, "Novela");
        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        CategoriaDTO result = categoriaService.findById(1L);
        Assertions.assertNotNull(result);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNombre()).isEqualTo("Novela");
    }

    @Test
    void consultarPorId_noEncontrado() {
        Mockito.when(categoriaRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoriaService.findById(99L));
    }

    @Test
    void crearCategoria_DuplicadoNombre() {
        CategoriaDTO categoriaDTO = new CategoriaDTO(null, "Novela");
        Mockito.when(categoriaRepository.existsByNombre("Novela")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> categoriaService.save(categoriaDTO));
        Mockito.verify(categoriaRepository, Mockito.never()).save(Mockito.any(Categoria.class));
    }

    @Test
    void eliminarCategoria() {
        Mockito.when(categoriaRepository.existsById(1L)).thenReturn(true);
        categoriaService.deleteById(1L);
        Mockito.verify(categoriaRepository).deleteById(1L);
    }

    @Test
    void eliminarCategoria_noEncontrado() {
        Mockito.when(categoriaRepository.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> categoriaService.deleteById(99L));
        Mockito.verify(categoriaRepository, Mockito.never()).deleteById(1L);
    }
}
