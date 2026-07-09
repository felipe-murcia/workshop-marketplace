package com.example.libreria.controller;


import com.example.libreria.dto.CategoriaDTO;
import com.example.libreria.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CategoriaService service;

    @Test
    void GET_categorias_devuelve200yLaLista() throws Exception {
        when(service.findAll()).thenReturn(List.of(
                new CategoriaDTO(1L, "Novela")
        ));

        mvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Novela"));
    }

    @Test
    void GET_categoria_devuelve200yData() throws Exception {
        when(service.findById(1L)).thenReturn(new CategoriaDTO(1L, "Novela"));

        mvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Novela"));
    }

    @Test
    void GET_categoria_devuelve404() throws Exception {
        when(service.findById(22L)).thenThrow(new EntityNotFoundException());
        mvc.perform(get("/api/categorias/22"))
                .andExpect(status().isNotFound());
    }

    @Test
    void POST_categoria_devuelve201yData() throws Exception {

        CategoriaDTO request = new CategoriaDTO(1L, "Novela");
        CategoriaDTO response = new CategoriaDTO(1L, "Novela");

        when(service.save(any(CategoriaDTO.class))).thenReturn(response);

        mvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Novela\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Novela"));

    }

    @Test
    void POST_categoria_devuelveBadRequest() throws Exception {
        mvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void DELETE_eliminarCategoria_devuelve204() throws Exception {

        doNothing().when(service).deleteById(1L);

        mvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteById(1L);
    }

}
