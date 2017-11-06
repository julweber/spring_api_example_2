package training;

import training.Application;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    public void run(String...args) throws Exception {
      Application.logger.info("Application command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
    }
}
