package unit5;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import unit5.service.ConsoleService;

import java.io.IOException;

@SpringBootApplication
@AllArgsConstructor
public class ConsoleCrudApp implements CommandLineRunner {

    private final ConsoleService consoleService;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleCrudApp.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        consoleService.printWelcomeMessage();
        consoleService.printChoices();
    }
}
