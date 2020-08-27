package com.lysachenko.menu;

import com.lysachenko.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Menu {

    @Autowired
    private UserMenu userMenu;
    @Autowired
    private BookMenu bookMenu;
    private static final String MENU_ITEMS =
            "\n1. User menu" +
                    "\n2. Book menu" +
                    "\n0. Exit";

    public void show() {
        while (true) {
            System.out.println(MENU_ITEMS + "\n");
            System.out.print("Enter your choice: ");
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
