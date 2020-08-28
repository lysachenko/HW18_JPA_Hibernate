package com.lysachenko.menu.impl;

import com.lysachenko.menu.Menu;
import com.lysachenko.model.Book;
import com.lysachenko.service.BookService;
import com.lysachenko.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookMenu implements Menu {

    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public BookMenu(BookService bookService) {
        this.bookService = bookService;
    }

    private static final String MENU_ITEMS =
            "\n1. Add book" +
                    "\n2. Update book" +
                    "\n3. Delete book" +
                    "\n4. Show all books" +
                    "\n0. Return to the previous menu";

    public void show() {
        while (true) {
            showItems(MENU_ITEMS);
            int choice = ScannerUtil.getInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    showAllBooks();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void deleteBook() {
        showAllBooks();
        System.out.print("Enter book ID to delete: ");
        Long bookId = ScannerUtil.getLong();
        Book book = bookService.getById(bookId);
        if (book != null) {
            bookService.delete(book);
            System.out.println("Book deleted!");
        } else {
            System.out.println("Incorrect user ID!");
        }
    }

    private void showAllBooks() {
        System.out.println("Book list: ");
        bookService.getAll().forEach(System.out::println);
    }

    private void updateBook() {
        showAllBooks();
        System.out.print("Enter book ID to update: ");
        Long bookId = ScannerUtil.getLong();
        Book book = bookService.getById(bookId);
        if (book != null) {
            System.out.print("Enter name: ");
            book.setName(scanner.nextLine());

            System.out.print("Enter author: ");
            book.setAuthor(scanner.nextLine());
            bookService.update(book);
            System.out.println("User updated!");
        } else {
            System.out.println("Incorrect user ID!");
        }
    }

    private void addBook() {
        Book book = new Book();
        System.out.print("Enter book name: ");
        book.setName(scanner.nextLine());

        System.out.print("Enter book author: ");
        book.setAuthor(scanner.nextLine());

        bookService.create(book);
        System.out.println("Book created!");
    }

}
