package com.practicaParcial.practicaParcial.controller;

import com.practicaParcial.practicaParcial.model.Publicacion;
import com.practicaParcial.practicaParcial.model.Usuario;
import com.practicaParcial.practicaParcial.repository.PublicacionJpaRepository;
import com.practicaParcial.practicaParcial.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("publicaciones")
@RestController
public class PublicacionControladora {
    @Autowired
    private PublicacionJpaRepository publicacionJpaRepository;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @PostMapping("{id}/usuario")
    public void save(@Valid @RequestBody final Publicacion publicacion, @PathVariable int id_usuario){
        Usuario usuario =this.findUsuarioById(id_usuario);
        publicacion.setUsuario(usuario);
        this.publicacionJpaRepository.save(publicacion);
    }

    @GetMapping("")
    public List<Publicacion> findAll(){
        return this.publicacionJpaRepository.findAll();
    }

    private Usuario findUsuarioById(int id){
        return this.usuarioJpaRepository.findById(id).orElseThrow( () ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST,"NO SE ENCONTRO EL ID"));
    }
}
