package training.messaging;

import training.Application;
import training.concepts.message.Message;
import training.concepts.message.operations.MessageCreate;
import training.messaging.MessageParser;

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

    createMessageFromJson(message);

    latch.countDown();
  }

  public CountDownLatch getLatch() {
      return latch;
  }

  private void createMessageFromJson(String message) {
    try {
      Application.logger.info("Parsing JSON to Message object");
      MessageParser parser = new MessageParser();
      Message messageObj = parser.parseJson(message);
      Application.logger.info("Parsed Message object: {}", messageObj);

      Application.logger.info("Calling MessageCreate operation...");
      Map<String, Object> payload = new HashMap<String, Object>();
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("message", messageObj);
      payload.put("params", params);

      createOperation.run(payload);
    } catch(ParseException ex) {
      Application.logger.info("An error occured while creating Message from JSON: {}", ex);
    }
  }

}
