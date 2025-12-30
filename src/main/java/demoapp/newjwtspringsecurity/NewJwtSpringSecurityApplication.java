package demoapp.newjwtspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NewJwtSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewJwtSpringSecurityApplication.class, args);
    }

}
