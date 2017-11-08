package training.messaging;

import training.concepts.message.Message;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class MessageParser {
  private JSONParser parser;

  public MessageParser() {
    this.parser = new JSONParser();
  }

  // parse JSON to a Message object
  public Message parseJson(String jsonString) throws ParseException {
    JSONObject obj = (JSONObject) this.parser.parse(jsonString);
    String recipient = (String) obj.get("recipient");
    String header = (String) obj.get("header");
    String body = (String) obj.get("body");

    return new Message(recipient, header, body);
  }
}
