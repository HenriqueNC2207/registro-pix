package dev.akif.library;

import dev.akif.crud.common.InstantProvider;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@OpenAPIDefinition(
        info = @Info(
                title = "Pix API",
                version = "1.0.0",
                description = "An API for a PIX application system"
        )
)
@RestControllerAdvice
@SpringBootApplication
public class Main extends ExceptionHandlerAdvice {
    @Bean
    public InstantProvider instantProvider() {
        return InstantProvider.utc;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
