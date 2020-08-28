package com.lysachenko.menu.impl;

import com.lysachenko.menu.Menu;
import com.lysachenko.model.Book;
import com.lysachenko.model.User;
import com.lysachenko.service.BookService;
import com.lysachenko.service.UserService;
import com.lysachenko.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class UserMenu implements Menu {

    private final UserService userService;
    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public UserMenu(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    private static final String MENU_ITEMS =
            "\n1. Add user" +
                    "\n2. Update user" +
                    "\n3. Delete user" +
                    "\n4. Show all users" +
                    "\n5. Show user by ID" +
                    "\n6. Show users by name" +
                    "\n7. Add book to user" +
                    "\n8. Remove book from user" +
                    "\n0. Return to the previous menu";

    public void show() {
        while (true) {
            showItems(MENU_ITEMS);
            int choice = ScannerUtil.getInt();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    showAllUsers();
                    break;
                case 5:
                    showUserById();
                    break;
                case 6:
                    showUserByName();
                    break;
                case 7:
                    addBookToUser();
                    break;
                case 8:
                    removeBookFromUser();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void removeBookFromUser() {
        showUsersWithBooks();
        System.out.print("Choose and enter user ID: ");
        Long userId = ScannerUtil.getLong();
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User with ID: " + userId + "not exist!");
            return;
        }

        System.out.println("Users book:");
        user.getBooks().forEach(System.out::println);

        System.out.print("Choose one to remove, ID: ");
        Long bookId = ScannerUtil.getLong();
        Book book = bookService.getById(bookId);
        if (book == null) {
            System.out.println("Book with ID: " + bookId + " not exist!");
            return;
        }
        userService.removeBook(user, book);
        System.out.println("Book remover from user: " + user.getId() + ": " + user.getName());
    }

    private void addBookToUser() {
        showAllUsers();
        System.out.print("Choose and enter user ID: ");
        Long userId = ScannerUtil.getLong();
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User with ID: " + userId + "not exist!");
            return;
        }

        System.out.println("Book list:");
        if (getAllFreeBooks().isEmpty()) {
            System.out.println("No free book!");
            return;
        }
        getAllFreeBooks().forEach(System.out::println);
        System.out.print("Choose and enter book ID: ");
        Long bookId = ScannerUtil.getLong();
        Book book = bookService.getById(bookId);
        if (book == null) {
            System.out.println("Book with ID: " + bookId + "not exist!");
            return;
        }
        userService.addBook(user, book);
        System.out.println("Book added!");
    }

    private List<Book> getAllFreeBooks() {
        List<Book> books = bookService.getAll();
        return books.stream()
                .filter(book -> book.getUser() == null)
                .collect(Collectors.toList());
    }

    private void showUserByName() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        List<User> users = userService.getByName(name);
        if (users.isEmpty()) {
            System.out.println("No matches!");
        } else {
            System.out.println("Users with name" + name);
            users.forEach(System.out::println);
        }
    }

    private void showUserById() {
        System.out.print("Enter user ID: ");
        Long id = ScannerUtil.getLong();
        User user = userService.getById(id);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with ID: " + id + " not exist!");
        }
    }

    private void updateUser() {
        showAllUsers();
        System.out.print("Enter user ID to update: ");
        Long id = ScannerUtil.getLong();
        User user = userService.getById(id);
        if (user != null) {
            System.out.print("Enter new name: ");
            user.setName(scanner.nextLine());
            userService.update(user);
            System.out.println("User updated!");
        } else {
            System.out.println("Incorrect user ID!");
        }
    }

    private void showAllUsers() {
        System.out.println("User list:");
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void showUsersWithBooks() {
        System.out.println("User list:");
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            users.stream()
                    .filter(user -> !user.getBooks().isEmpty())
                    .forEach(System.out::println);
        }
    }

    private void deleteUser() {
        showAllUsers();
        System.out.print("Enter user id to delete: ");
        Long id = ScannerUtil.getLong();
        User user = userService.getById(id);
        if (user != null) {
            userService.delete(user);
            System.out.println("User deleted!");
        } else {
            System.out.println("Incorrect user ID!");
        }
    }

    private void addUser() {
        User user = new User();
        System.out.print("Enter user name: ");
        user.setName(scanner.nextLine());

        userService.create(user);
        System.out.println("User created!");
    }
}
