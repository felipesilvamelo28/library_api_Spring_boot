package com.felipe.library.controller;

import com.felipe.library.entity.Author;
import com.felipe.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/library/authors")
public class AuthorController {

    @Autowired
    AuthorRepository repository;

    //Listando todos os autores:

    @GetMapping
    List<Author> findAllAuthors(){
        return repository.findAll();
    }

    //Buscando autor por id:

    @GetMapping(value = "/{id}")
    public Optional<Author> findAuthorById(@PathVariable Long id) {
        return repository.findById(id);
    }

    //Inserindo autor:

    @PostMapping
    public Author author(@RequestBody Author author){
        return repository.save(author);
    }

    //Deletando autor:

    @DeleteMapping("/{id}")
    public List<Author> deleteBook(@PathVariable Long id){
        repository.deleteById(id);
        return repository.findAll();
    }

    //Atualizando autor:

    @PutMapping
    public Author updateBook(@RequestBody Author author){
        return repository.save(author);
    }

}
