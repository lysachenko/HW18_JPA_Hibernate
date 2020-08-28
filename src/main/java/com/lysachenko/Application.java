package com.lysachenko;

import com.lysachenko.config.Config;
import com.lysachenko.menu.impl.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        var annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(Config.class);

        MainMenu mainMenu = annotationConfigApplicationContext.getBean(MainMenu.class);
        mainMenu.show();
    }
}
