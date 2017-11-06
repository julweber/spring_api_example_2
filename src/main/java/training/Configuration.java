package training;

// application configuration from environment variables: https://12factor.net/config

public class Configuration {

  // good example for port binding from environment variables -> PaaS standard
  public final Integer PORT = (System.getenv("PORT") != null) ? Integer.parseInt(System.getenv("PORT")) : 8081;

  public String toString() {
    String str = "";
    str += String.format("PORT: %d \n", PORT);
    return str;
  }
}
