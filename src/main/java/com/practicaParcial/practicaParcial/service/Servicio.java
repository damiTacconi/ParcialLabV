package com.practicaParcial.practicaParcial.service;

import com.practicaParcial.practicaParcial.model.Comentario;
import com.practicaParcial.practicaParcial.model.Publicacion;
import com.practicaParcial.practicaParcial.model.Usuario;
import com.practicaParcial.practicaParcial.repository.ComentarioJpaRepository;
import com.practicaParcial.practicaParcial.repository.PublicacionJpaRepository;
import com.practicaParcial.practicaParcial.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class Servicio {

    @Autowired
    protected ComentarioJpaRepository comentarioJpaRepository;
    @Autowired
    protected UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    protected PublicacionJpaRepository publicacionJpaRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Publicacion>> getPublicaciones()
    {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(this.publicacionJpaRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Comentario>> getComentarios()
    {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(this.comentarioJpaRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Usuario>> getUsuarios()
    {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(this.usuarioJpaRepository.findAll());
    }


}
