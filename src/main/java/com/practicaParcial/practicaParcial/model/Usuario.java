package com.practicaParcial.practicaParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
    @GeneratedValue
    @Id
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String apellido;
    private String browser;

    @JsonIgnoreProperties("usuario")
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "usuario")
    List<Publicacion> publicaciones;

    @JsonIgnoreProperties("usuario")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    List<Comentario> comentarios;
}
