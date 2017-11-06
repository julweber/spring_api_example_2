package training.concepts.message.representers;

import training.concepts.message.Message;
import training.concepts.application.Representer;

public class ShortRepresenter extends Representer {

  protected Message message;

  protected ShortRepresenter() {}

  public ShortRepresenter(Message message) {
    this.message = message;
  }

  public Long getId() {
    if (this.message == null) {
      return null;
    }
    return this.message.getId();
  }

  public String getRecipient() {
    if (this.message == null) {
      return null;
    }
    return this.message.getRecipient();
  }

  public String getHeader() {
    if (this.message == null) {
      return null;
    }
    return this.message.getHeader();
  }

}
