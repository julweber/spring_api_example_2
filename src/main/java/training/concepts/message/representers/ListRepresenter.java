package training.concepts.message.representers;

import training.concepts.message.Message;
import training.concepts.message.representers.ShortRepresenter;
import training.concepts.application.Representer;

import java.util.List;
import java.util.ArrayList;

public class ListRepresenter extends Representer {
  protected List<ShortRepresenter> resources;

  public ListRepresenter(List<Message> messages) {
    this.resources = convertToRepresentedList(messages);
  }

  public Integer getCount() {
    return resources.size();
  }

  public List<ShortRepresenter> getResources() {
    return this.resources;
  }

  // helper method for initializing the representer list
  private List convertToRepresentedList(List<Message> inputList) {
    List<ShortRepresenter> list = new ArrayList<ShortRepresenter>();

    for (Message msg : inputList) {
      ShortRepresenter rep = new ShortRepresenter(msg);
      list.add(rep);
    }
    return list;
  }
}
