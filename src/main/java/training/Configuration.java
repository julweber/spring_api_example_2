package training;

// application configuration from environment variables: https://12factor.net/config

public class Configuration {

  // good example for port binding from environment variables -> PaaS standard
  public final Integer PORT = (System.getenv("PORT") != null) ? Integer.parseInt(System.getenv("PORT")) : 8081;
  public final String QUEUE_NAME = (System.getenv("QUEUE_NAME") != null) ? System.getenv("QUEUE_NAME") : "messages";

  public String toString() {
    String str = "";
    str += String.format("PORT: %d \n", PORT);
    str += String.format("QUEUE_NAME: %s \n", QUEUE_NAME);
    return str;
  }
}
