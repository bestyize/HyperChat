package xyz.thewind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.thewind.global.util.ApplicationUtils;

@SpringBootApplication
@EnableJpaRepositories(value = {"xyz.thewind.music.repository", "xyz.thewind.user.database.repo"})
public class HyperChatApplication {

    public static void main(String[] args) {
        ApplicationUtils.CONTEXT = SpringApplication.run(HyperChatApplication.class, args);
    }

}
