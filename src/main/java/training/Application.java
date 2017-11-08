package training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

// RabbitMQ includes
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import training.messaging.Receiver;

import training.Configuration;
import training.concepts.message.MessageRepository;
import training.concepts.message.Message;

@SpringBootApplication
@EnableJpaRepositories(basePackages="training")
public class Application {

  public static final Logger logger = LoggerFactory.getLogger(Application.class);
  public static final Configuration configuration = new Configuration();
  public final static String queueName = configuration.QUEUE_NAME;

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

  // RabbitMQ configuration
  @Bean
  Queue queue() {
      return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
      return new TopicExchange("spring-boot-exchange");
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with(queueName);
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
          MessageListenerAdapter listenerAdapter) {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      container.setQueueNames(queueName);
      container.setMessageListener(listenerAdapter);
      return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
      return new MessageListenerAdapter(receiver, "receiveMessage");
  }

}
