// // Uncomment the runner below to run on startup using gradle bootRun ->
// // This will crash the application after the runner finishes
// // IMPORTANT: Comment out again after testing

// package training;
//
// import training.Application;
// import training.messaging.Receiver;
// import training.messaging.MessageFormatter;
//
// import java.util.concurrent.TimeUnit;
// import java.io.IOException;
//
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.stereotype.Component;
//
//
// @Component
// public class RabbitMqRunner implements CommandLineRunner {
//
//     private final RabbitTemplate rabbitTemplate;
//     private final Receiver receiver;
//     private final ConfigurableApplicationContext context;
//
//     public RabbitMqRunner(Receiver receiver, RabbitTemplate rabbitTemplate,
//             ConfigurableApplicationContext context) {
//         this.receiver = receiver;
//         this.rabbitTemplate = rabbitTemplate;
//         this.context = context;
//     }
//
//     @Override
//     public void run(String... args) throws Exception {
//       try {
//         MessageFormatter formatter = new MessageFormatter();
//         String message = formatter.generateJsonString("test@example.com",
//           "My Header",
//           "This is my message!");
//         Application.logger.info("Sending message: {}", message);
//         rabbitTemplate.convertAndSend(Application.queueName, message);
//         receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//         context.close();
//       }
//       catch (IOException ex) {
//         Application.logger.info("Exception while sending: {}", ex);
//       }
//     }
//
// }
