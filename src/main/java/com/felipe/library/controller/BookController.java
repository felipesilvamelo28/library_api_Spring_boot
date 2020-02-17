package com.felipe.library.controller;

import com.felipe.library.entity.Book;
import com.felipe.library.repository.BookRepository;
import com.felipe.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/library/books")
public class BookController {

    @Autowired
    BookRepository repository;
    @Autowired
    BookService bookService;

    //Listando todos os livros:

    @GetMapping
    public List<Book> findAllBooks(){
        return repository.findAll();
    }

    //Buscando livro por id:

    @GetMapping(value = "/{id}")
    public Optional<Book> findBookById(@PathVariable Long id) {
        return repository.findById(id);
    }

    //Inserindo livro:

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return repository.save(bookService.save(book));
    }

    //Deletando livro:

    @DeleteMapping("/{id}")
    public List<Book> deleteBook(@PathVariable Long id){
        repository.deleteById(id);
        return repository.findAll();
    }

    //Atualizando livro:

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return repository.save(bookService.saveBookUpdated(book));
    }
}
