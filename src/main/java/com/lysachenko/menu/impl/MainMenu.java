package com.lysachenko.menu.impl;

import com.lysachenko.menu.Menu;
import com.lysachenko.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu implements Menu {

    private final UserMenu userMenu;
    private final BookMenu bookMenu;
    private static final String MENU_ITEMS =
            "\n1. User menu" +
                    "\n2. Book menu" +
                    "\n0. Exit";

    @Autowired
    public MainMenu(UserMenu userMenu, BookMenu bookMenu) {
        this.userMenu = userMenu;
        this.bookMenu = bookMenu;
    }

    public void show() {
        while (true) {
            showItems(MENU_ITEMS);
            int choice = ScannerUtil.getInt();

            switch (choice) {
                case 1:
                    userMenu.show();
                    break;
                case 2:
                    bookMenu.show();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
