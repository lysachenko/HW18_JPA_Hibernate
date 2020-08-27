package com.lysachenko;

import com.lysachenko.config.Config;
import com.lysachenko.menu.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        var annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(Config.class);

        Menu menu = annotationConfigApplicationContext.getBean(Menu.class);
        menu.show();
    }
}
