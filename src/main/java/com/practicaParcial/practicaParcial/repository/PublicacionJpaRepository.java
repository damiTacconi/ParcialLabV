package com.practicaParcial.practicaParcial.repository;

import com.practicaParcial.practicaParcial.dto.CantidadComentarios;
import com.practicaParcial.practicaParcial.interfaces.cantidadComentarios;
import com.practicaParcial.practicaParcial.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicacionJpaRepository extends JpaRepository<Publicacion,Integer> {

    String queryCantidadComentarios =
            "select p.titulo , u.nombre, count(c.id) as cantidad from publicaciones p inner join usuarios u inner join comentarios c " +
                    "on p.usuario_id=u.id AND c.publicacion_id = p.id where p.id= :id GROUP BY p.titulo";


    @Query(value = queryCantidadComentarios , nativeQuery = true)
    cantidadComentarios getCantidadComentarios(@Param("id") int id);

    @Query(value = queryCantidadComentarios, nativeQuery = true)
    CantidadComentarios getCantidadComentarios2(@Param("id") int id);
}
