package com.practicaParcial.practicaParcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "comentarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comentario {
    @GeneratedValue
    @Id
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "America/Los_Angeles")
    private LocalDateTime fecha;

    @JsonIgnoreProperties("comentarios")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String descripcion;


    @JsonIgnoreProperties("comentarios")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    @PrePersist
    public void agregarFecha(){

        if(Objects.isNull(fecha)){
            this.fecha = LocalDateTime.now();
        }
    }

}
