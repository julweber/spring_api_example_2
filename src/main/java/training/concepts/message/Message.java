package training.concepts.message;

import javax.persistence.*;

@Entity
public class Message {

  @Id
  @SequenceGenerator(name = "seq_messages", sequenceName = "seq_messages")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_messages")
  private Long id;
  private String recipient;
  private String header;
  private String body;


  protected Message() {}

  public Message(String recipient, String header, String body) {
    this.recipient = recipient;
    this.header = header;
    this.body = body;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRecipient() {
    return this.recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getHeader() {
    return this.header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
      return String.format(
              "Message[id=%d, recipient='%s', header='%s', body='%s']",
              id, recipient, header, body);
  }

}
