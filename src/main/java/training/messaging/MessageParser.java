package training.messaging;

import training.concepts.message.Message;

import org.json.simple.JSONObject;

public class MessageParser {

  // parse JSON to a Message object
  public Message parse(JSONObject json) {
    String recipient = (String) json.get("recipient");
    String header = (String) json.get("header");
    String body = (String) json.get("body");

    return new Message(recipient, header, body);
  }
}
