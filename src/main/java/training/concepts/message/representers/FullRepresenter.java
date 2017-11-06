package training.concepts.message.representers;

import training.concepts.message.Message;

// FullRepresenters derives from ShortRepresenter to have all fields of the short representation
// plus the fields for a full representation of a Message
public class FullRepresenter extends ShortRepresenter {

  protected FullRepresenter() {}

  public FullRepresenter(Message message) {
    this.message = message;
  }

  public String getBody() {
    if (this.message == null) {
      return null;
    }
    return this.message.getBody();
  }
}
