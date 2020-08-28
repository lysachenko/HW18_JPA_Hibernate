package com.lysachenko.menu;

public interface Menu {

    void show();

    default void showItems(String items) {
        System.out.println(items + "\n");
        System.out.print("Enter your choice: ");
    }
}
