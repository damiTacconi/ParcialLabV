package com.practicaParcial.practicaParcial.dto;


import com.practicaParcial.practicaParcial.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;


@EqualsAndHashCode
@AllArgsConstructor
@lombok.Value
public class CantidadComentarios {
    @Value("${target.titulo}")
    private String titulo;
    @Value("#{target.nombre}")
    private String nombre;
    @Value("${target.cantidad}")
    private Integer cantidad;

}
