package com.practicaParcial.practicaParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class Comentario {
    @GeneratedValue
    @Id
    private int id;
    private LocalDateTime fecha;

    @JsonIgnoreProperties("comentarios")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario owner;


    @JsonIgnoreProperties("comentarios")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comentario_id", referencedColumnName = "id")
    private Publicacion publicacion;

    @PrePersist
    public void agregarFecha(){

        if(Objects.isNull(fecha)){
            this.fecha = LocalDateTime.now();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
            format.format(fecha);
        }
    }

}
