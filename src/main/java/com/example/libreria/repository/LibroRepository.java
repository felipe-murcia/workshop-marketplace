package com.example.libreria.repository;

import com.example.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
    Optional<Libro> findByIsbn(@Param("isbn") String isbn);
    //List<Libro> findByIsbn(@Param("isbn") String isbn);
}
