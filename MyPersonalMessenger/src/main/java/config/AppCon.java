package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface AppCon {
    static ApplicationContext annotationConfig() {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigBase.class);
        return context;
    }
}
