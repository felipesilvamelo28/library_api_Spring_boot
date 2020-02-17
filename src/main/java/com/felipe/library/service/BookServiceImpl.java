package com.felipe.library.service;

import com.felipe.library.entity.Author;
import com.felipe.library.entity.Book;
import com.felipe.library.repository.AuthorRepository;
import com.felipe.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            //Verificando se o autor foi cadastrado com nome e cpf:

            if(book.getAuthor().getCpf() != null && book.getAuthor().getName() != null){

                //Verificando se o autor já existe:

                if(authorRepository.findByCpf(book.getAuthor().getCpf()).size() != 0){
                    throw new RuntimeException("Autor já existe!");
                } else {
                    return createBookWithAuthor(book);
                }
            } else {
                throw new RuntimeException("Falta Nome e/ou CPF do Autor!");
            }
        } else {
            throw new RuntimeException("É necessário cadastrar um Autor!");
        }
    }

    private Book createBookWithAuthor(Book book) {
        String authorCpf = book.getAuthor().getCpf();
        String authorName = book.getAuthor().getName();
        Author newAuthor = Author.builder().name(authorName).cpf(authorCpf).build();
        authorRepository.save(newAuthor);
        return bookRepository.save(book);
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
