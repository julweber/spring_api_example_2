package training.api.message;

import training.Application;
import training.api.BaseController;
import training.concepts.message.operations.MessageCreate;
import training.concepts.message.operations.MessageShow;
import training.concepts.message.operations.MessageListAll;
import training.concepts.message.Message;
import training.concepts.application.Representer;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MessageControllerV1 extends BaseController {

  @Autowired
  private MessageCreate createOperation;

  @Autowired
  private MessageShow showOperation;

  @Autowired
  private MessageListAll listOperation;

  // GET a list of messages -> message.MessageListAll operation
  @RequestMapping(value="/v1/messages", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> list() {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    payload.put("params", params);

    Map<String, Object> result = listOperation.run(payload);
    Representer list = (Representer) result.get("representer");

    ResponseEntity entity = new ResponseEntity<Representer>(list,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // POST new message -> message.MessageCreate operation
  @RequestMapping(value = "/v1/messages", method = RequestMethod.POST,
    produces = "application/json", consumes = "application/json")
  public ResponseEntity<?> create(@RequestBody Message message) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("message", message);
    payload.put("params", params);

    Map<String, Object> result = createOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // GET single message -> message.MessageShow operation
  @RequestMapping(value="/v1/messages/{messageId}", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> get(@PathVariable("messageId") Long id) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("messageId", id);
    payload.put("params", params);

    Map<String, Object> result = showOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

}
