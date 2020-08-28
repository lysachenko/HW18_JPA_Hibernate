package com.lysachenko.repository;

import com.lysachenko.model.Book;
import com.lysachenko.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class BookRepository {

    private static final String GET_ALL_BOOKS = "select b from Book b";
    private static final String GET_BOOK_BY_ID = "select b from Book b where b.id = ?1";

    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addBookToUser(User user, Book book) {
        book.setUser(user);
        this.update(book);
    }

    public void removeBookFromUser(User user, Book book) {
        if (user.equals(book.getUser())) {
            book.setUser(null);
            this.update(book);
        }
    }

    public void save(Book book) {
        getCurrentSession().save(book);
    }

    public void update(Book book) {
        getCurrentSession().update(book);
    }

    public void delete(Book book) {
        getCurrentSession().delete(book);
    }

    public List<Book> getAll() {
        return getCurrentSession().createQuery(GET_ALL_BOOKS, Book.class).getResultList();
    }

    public Book getBookById(Long id) {
        try {
            return getCurrentSession()
                    .createQuery(GET_BOOK_BY_ID, Book.class)
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
