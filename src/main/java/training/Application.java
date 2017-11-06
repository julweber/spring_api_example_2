package training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import training.Configuration;
import training.concepts.message.MessageRepository;
import training.concepts.message.Message;

@SpringBootApplication
@EnableJpaRepositories(basePackages="training")
public class Application {

  public static final Logger logger = LoggerFactory.getLogger(Application.class);
  public static final Configuration configuration = new Configuration();

  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  // Initialize seeds
  @Bean
  CommandLineRunner seed(MessageRepository repo) {
    return args -> {
        // Log out configuration
        logger.info("Configuration:");
        logger.info(configuration.toString());

        if (repo.count() < 1) {
          repo.save(new Message("julianweberdev@gmail.com", "Reminder", "Wake me up!"));
          repo.save(new Message("jimi@hendrix.com", "Prayer", "Please revive!"));
          repo.save(new Message("trinity@zion.com", "Hello", "Follow the white rabbit!"));

          System.out.println("---------------------------------");
          repo.findAll().forEach(System.out::println);
          System.out.println("---------------------------------");
        }
    };
  }

}
