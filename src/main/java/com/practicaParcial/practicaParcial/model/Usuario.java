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

    @JsonIgnoreProperties("owner")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    List<Comentario> comentarios;
}
