package com.practicaParcial.practicaParcial.controller;

import com.practicaParcial.practicaParcial.model.Usuario;
import com.practicaParcial.practicaParcial.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("usuarios")
@RestController
public class UsuarioControladora {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;


    @PostMapping("")
    public void save(@RequestHeader("user-agent") String browser,
                     @Valid @RequestBody final Usuario usuario)
    {
        usuario.setBrowser(browser);
        this.usuarioJpaRepository.save(usuario);
    }

    @GetMapping("")
    public List<Usuario> findAll(){
        return this.usuarioJpaRepository.findAll();
    }

    @PutMapping("{id}/update")
    public void update(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario usuario_old = this.findById(id);
        usuario_old.setNombre(usuario.getNombre());
        usuario_old.setApellido(usuario.getApellido());
        usuario_old.setBrowser(usuario.getBrowser());
        this.usuarioJpaRepository.save(usuario_old);

    }


    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable int id){

    }

    private Usuario findById(int id){
        return this.usuarioJpaRepository.findById(id).orElseThrow( () ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST,"NO SE ENCONTRO EL ID"));
    }
}
