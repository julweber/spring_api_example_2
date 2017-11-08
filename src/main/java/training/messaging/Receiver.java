package training.messaging;

import training.Application;
import training.concepts.message.Message;
import training.concepts.message.operations.MessageCreate;
import training.messaging.MessageParser;
import training.messaging.AmqpParser;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.HashMap;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.parser.ParseException;


@Component
public class Receiver {

  @Autowired
  private MessageCreate createOperation;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    Application.logger.info("Received <{}>", message);

    processAmqpMessage(message);

    latch.countDown();
  }

  public CountDownLatch getLatch() {
      return latch;
  }

  private void processAmqpMessage(String message) {
    try {
      Application.logger.info("Parsing JSON to Message object");
      AmqpParser parser = new AmqpParser();
      JSONObject amqpMessage = parser.parseJson(message);
      Application.logger.info("Parsed AMQP message: {}", amqpMessage);
      Application.logger.info("AMQP message type: {}", (String) amqpMessage.get("type"));

      processMessageMessage(amqpMessage);
      processRecordMessage(amqpMessage);
    } catch(ParseException ex) {
      Application.logger.info("An error occured while processing AMQP message from JSON: {}", ex);
    }
  }

  private void processMessageMessage(JSONObject amqpMessage) {
    Application.logger.info("Executing Receiver#processMessageMessage ...");
    String messageType = (String) amqpMessage.get("type");
    if (messageType != null && "Message".equals(messageType)) {
      Application.logger.info("Found type=Message within the AMQP message!");
      Application.logger.info("Calling MessageCreate operation...");
      Map<String, Object> payload = new HashMap<String, Object>();
      Map<String, Object> params = new HashMap<String, Object>();

      MessageParser msgParser = new MessageParser();
      Message message = msgParser.parse(amqpMessage);
      params.put("message", message);
      payload.put("params", params);

      createOperation.run(payload);
    }
  }

  private void processRecordMessage(JSONObject amqpMessage) {
    Application.logger.info("Executing Receiver#processRecordMessage ...");
    String messageType = (String) amqpMessage.get("type");

    if (messageType != null && "Record".equals(messageType)) {
      Application.logger.info("Found type=Record within the AMQP message!");
      Application.logger.info("Calling MessageCreate operation...");
      Map<String, Object> payload = new HashMap<String, Object>();
      Map<String, Object> params = new HashMap<String, Object>();

      String recipient = (String) amqpMessage.get("customerEmail");
      String recordTitle = (String) amqpMessage.get("title");
      String recordArtist = (String) amqpMessage.get("artist");

      String header = String.format("Added record: %s", recordTitle);
      String body = String.format("Added record: %s - %s to your collection!", recordArtist, recordTitle);

      Message message = new Message(recipient,
        recordTitle,
        recordArtist);
      params.put("message", message);
      payload.put("params", params);

      createOperation.run(payload);
    }
  }

}
