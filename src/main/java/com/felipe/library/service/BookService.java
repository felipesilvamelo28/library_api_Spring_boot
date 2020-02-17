package com.felipe.library.service;

import com.felipe.library.entity.Book;

public interface BookService {

    Book save(Book book);

    Book saveBookUpdated(Book book);
}
