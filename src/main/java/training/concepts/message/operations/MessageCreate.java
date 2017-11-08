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
public class MessageCreate implements OperationInterface {

  @Autowired
  private MessageRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Map params = (Map) payload.get("params");
    Message model = (Message) params.get("message");

    // require recipient
    if (model.getRecipient() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_MESSAGE_RECIPIENT",
      "No message recipient was provided!");
      payload.put("representer", rep);
      return payload;
    }

    // require message body
    if (model.getBody() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_MESSAGE_BODY",
      "No message body was provided!");
      payload.put("representer", rep);
      return payload;
    }

    repository.save(model);
    Application.logger.info("Created message: {}", model);
    payload.put("httpStatus", HttpStatus.CREATED);
    payload.put("model", model);
    FullRepresenter rep = new FullRepresenter(model);
    payload.put("representer", rep);
    return payload;
  }
}
