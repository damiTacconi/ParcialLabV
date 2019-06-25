package com.practicaParcial.practicaParcial.controller;

import com.practicaParcial.practicaParcial.model.Comentario;
import com.practicaParcial.practicaParcial.model.Publicacion;
import com.practicaParcial.practicaParcial.model.Usuario;
import com.practicaParcial.practicaParcial.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
@RequestMapping("api")
public class AppController {

    @Autowired
    Servicio servicio;

    @GetMapping("all")
    public void allContent()
    {

        CompletableFuture<List<Publicacion>> publicaciones = servicio.getPublicaciones();
        CompletableFuture<List<Usuario>> usuarios = servicio.getUsuarios();
        CompletableFuture<List<Comentario>> comentarios = servicio.getComentarios();

        publicaciones.join();
        usuarios.join();
        comentarios.join();

        try {
            publicaciones.get().forEach(System.out::println);
            usuarios.get().forEach(System.out::println);
            comentarios.get().forEach(System.out::println);
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
