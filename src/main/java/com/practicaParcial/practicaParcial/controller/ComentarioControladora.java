package com.practicaParcial.practicaParcial.controller;

import com.practicaParcial.practicaParcial.model.Comentario;
import com.practicaParcial.practicaParcial.model.Publicacion;
import com.practicaParcial.practicaParcial.model.Usuario;
import com.practicaParcial.practicaParcial.repository.ComentarioJpaRepository;
import com.practicaParcial.practicaParcial.repository.PublicacionJpaRepository;
import com.practicaParcial.practicaParcial.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequestMapping("comentarios")
@RestController
public class ComentarioControladora {

    @Autowired
    private ComentarioJpaRepository comentarioJpaRepository;
    @Autowired
    private PublicacionJpaRepository publicacionJpaRepository;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @PostMapping("{id_publicacion}/publicacion/")
    public void save(@Valid @RequestBody final Comentario comentario,
                     @PathVariable int id_publicacion){
        Publicacion publicacion = this.findPublicacionById(id_publicacion);
        comentario.setOwner(publicacion.getUsuario());
        comentario.setPublicacion(publicacion);
        this.comentarioJpaRepository.save(comentario);
    }

    @GetMapping("")
    public List<Comentario> findAll(){
        return this.comentarioJpaRepository.findAll();
    }


    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable int id){
        Comentario comentario = this.findById(id);
        this.comentarioJpaRepository.delete(comentario);
    }


    private Comentario findById(int id){
        return this.comentarioJpaRepository.findById(id).orElseThrow( () ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST,"NO SE ENCONTRO EL ID"));
    }


    @Scheduled(fixedRate = 10000)
    public void eliminarComentarios(){
        List<Comentario> comentarios = this.comentarioJpaRepository.findAll();
        comentarios.forEach(c -> {
            long horas = ChronoUnit.HOURS.between(c.getFecha(), LocalDateTime.now());
            if(horas >= 2 ){
                this.comentarioJpaRepository.delete(c);
            }
        });
    }

    private Publicacion findPublicacionById(int id){
        return this.publicacionJpaRepository.findById(id).orElseThrow( () ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST,"NO SE ENCONTRO EL ID"));
    }
}
