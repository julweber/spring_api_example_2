package training.messaging;

import training.concepts.message.Message;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class AmqpParser {
  private JSONParser parser;

  public AmqpParser() {
    this.parser = new JSONParser();
  }

  // parse JSON to a Message object
  public JSONObject parseJson(String jsonString) throws ParseException {
    JSONObject obj = (JSONObject) this.parser.parse(jsonString);
    return obj;
  }
}
