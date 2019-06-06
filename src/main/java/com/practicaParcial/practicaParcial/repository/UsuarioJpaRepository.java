package com.practicaParcial.practicaParcial.repository;

import com.practicaParcial.practicaParcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario,Integer> {
}
