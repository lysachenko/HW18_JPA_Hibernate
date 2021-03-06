package com.lysachenko.service;

import com.lysachenko.model.Book;
import com.lysachenko.model.User;
import com.lysachenko.repository.BookRepository;
import com.lysachenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public List<User> getByName(String name) {
        return userRepository.getUserByName(name);
    }

    public User getById(Long id) {
        return userRepository.getUserById(id);
    }

    public void addBook(User user, Book book) {
        bookRepository.addBookToUser(user, book);
    }

    public void removeBook(User user, Book book) {
        bookRepository.removeBookFromUser(user, book);
    }

}
