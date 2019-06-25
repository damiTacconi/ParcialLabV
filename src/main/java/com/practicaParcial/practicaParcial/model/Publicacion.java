package com.practicaParcial.practicaParcial.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "publicaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publicacion {
    @Id
    @GeneratedValue
    private Integer id;

    private String titulo;
    private String descripcion;
    private String foto;
    private LocalDateTime fecha;
    private int liked;


    @JsonIgnoreProperties("publicacion")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publicacion")
    private List<Comentario> comentarios;

    @JsonIgnoreProperties("publicaciones")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id" , referencedColumnName = "id")
    private Usuario usuario;

}
