package training;

// application configuration from environment variables: https://12factor.net/config

public class Configuration {

  public final Integer RESULTS_PER_PAGE = (System.getenv("RESULTS_PER_PAGE") != null) ? Integer.parseInt(System.getenv("RESULTS_PER_PAGE")) : 5;

  public String toString() {
    String str = "";
    str += String.format("RESULTS_PER_PAGE: %d \n", RESULTS_PER_PAGE);
    return str;
  }
}
