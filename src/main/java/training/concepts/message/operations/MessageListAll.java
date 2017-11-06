package training.concepts.message.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.message.Message;
import training.concepts.message.MessageRepository;
import training.concepts.message.representers.ListRepresenter;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class MessageListAll implements OperationInterface {

  @Autowired
  private MessageRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Iterable<Message> all = repository.findAll();
    List<Message> model = Lists.newArrayList(all);
    Application.logger.info("Found Messages: {}", model);
    ListRepresenter rep = new ListRepresenter(model);

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    payload.put("representer", rep);
    return payload;
  }
}
