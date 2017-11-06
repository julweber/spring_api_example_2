package training.concepts.message.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.message.Message;
import training.concepts.message.MessageRepository;
import training.concepts.message.representers.FullRepresenter;
import training.concepts.application.ErrorRepresenter;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class MessageShow implements OperationInterface {

  @Autowired
  private MessageRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    // fetch an individual message by ID
    Map params = (Map) payload.get("params");
    Long messageId = (Long) params.get("messageId");

    Message model = repository.findOne(messageId);

    Application.logger.info("Found Message: {}", model);

    if (model == null) {
      Application.logger.info("Could not find message with id: {}", messageId);
      ErrorRepresenter errorRepresenter = new ErrorRepresenter("NOT_FOUND",
        String.format("The message with id: %d could not be found!", messageId));
      payload.put("httpStatus", HttpStatus.NOT_FOUND);
      payload.put("representer", errorRepresenter);
      payload.put("model", null);
      return payload;
    } else {
      payload.put("httpStatus", HttpStatus.OK);
      FullRepresenter rep = new FullRepresenter(model);
      payload.put("representer", rep);
      payload.put("model", model);
      return payload;
    }

  }
}
