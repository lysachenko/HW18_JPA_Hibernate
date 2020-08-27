package com.lysachenko.service;

import com.lysachenko.model.Book;
import com.lysachenko.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void create(Book book) {
        bookRepository.save(book);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public Book getById(Long id) {
        return bookRepository.getBookById(id);
    }
}
