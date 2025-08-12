package app.komek.appkomek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppKomekApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppKomekApplication.class, args);
    }

}
