package com.felipe.library.service;

import com.felipe.library.entity.Author;
import com.felipe.library.entity.Book;
import com.felipe.library.repository.AuthorRepository;
import com.felipe.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;

    //Implementando método para adicionar Livro. É obrigatório adicionar um autor para o livro.

    @Override
    public Book save(Book book) {
        if (book.getAuthor() != null) {
            String authorCpf = book.getAuthor().getCpf();
            String authorName = book.getAuthor().getName();
            Author newAuthor = Author.builder().name(authorName).cpf(authorCpf).build();
            authorRepository.save(newAuthor);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("É necessário cadastrar um Autor!");
        }
    }

    //Implementando método para atualizar Livro. Se o livro não existe, deve adicionar um livro novo.

    @Override
    public Book saveBookUpdated(Book book) {
        if(bookRepository.findById(book.getId()).isPresent()){
            bookRepository.save(book);
        } else {
            book.getAuthor().setId(null);
        }
        return bookRepository.save(bookService.save(book));
    }
}
